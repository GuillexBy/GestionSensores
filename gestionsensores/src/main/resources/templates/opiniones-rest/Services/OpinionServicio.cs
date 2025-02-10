using System;
using System.Collections.Generic;
using System.Text;
using Opiniones.Modelo;
using Repositorio;
using MongoDB.Bson;
using RabbitMQ.Client;
using Newtonsoft.Json;

namespace Opiniones.Servicio
{
    public interface IServicioOpiniones
    {

        string Create(String recurso);

        void Update(Opinion opinion);

        Opinion Get(string id);

        void Remove(string id);

        void addValoracion(Valoracion valoracion, String id);

         List<string> GetIds();
        
    }

    public class ServicioOpiniones : IServicioOpiniones
    {
        private readonly String exchangeName = "evento.valoracion.creada";
        private readonly IConnection connection;
        private readonly IModel channel;
        private readonly String routingKey = "arsoRK";
        private Repositorio<Opinion, String> repositorio;

        public ServicioOpiniones(Repositorio<Opinion, String> repositorio, IConnection connection, IModel model)
        {
            this.repositorio = repositorio;
            this.connection = connection;
            this.channel = model;
           
        }


        public String Create(String recurso)    
        {

            Opinion opinion = new Opinion();
            opinion.Id = ObjectId.GenerateNewId().ToString();
            opinion.Recurso = recurso;
            repositorio.Add(opinion);
            return opinion.Id;
        }

        public void Update(Opinion opinion)
        {

            repositorio.Update(opinion);
        }

        public Opinion Get(String id)
        {
            return repositorio.GetById(id);
        }

        public void Remove(String id)
        {
            Opinion opinion = repositorio.GetById(id);
            repositorio.Delete(opinion);
        }

        public void addValoracion(Valoracion valoracion, String id){
            Opinion o = repositorio.GetById(id);
            if(o.Valoraciones.ContainsKey(valoracion.Correo))
                o.Valoraciones[valoracion.Correo] = valoracion;
            else
                o.Valoraciones.Add(valoracion.Correo, valoracion);
            repositorio.Update(o);

            EventoValoracionCreada evento = new EventoValoracionCreada();
            evento.idOpinion = o.Id;
            evento.valoracion = valoracion;
            ResumenOpinion resumen = new ResumenOpinion();
            resumen.calificacionMedia = o.CalificacionMedia;
            resumen.numeroValoraciones = o.NumValoraciones;
            resumen.id = o.Id;
            evento.resumenOpinion = resumen;

            notificarEvento(evento);
        }

        protected void notificarEvento(EventoValoracionCreada evento){
            string resumenJson = JsonConvert.SerializeObject(evento);
            channel.BasicPublish("evento.valoracion.creada", routingKey, null, Encoding.UTF8.GetBytes(resumenJson));
        }

        public List<string> GetIds(){
            return repositorio.GetIds();
        }
    }


}