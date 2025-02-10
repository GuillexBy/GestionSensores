using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System;
using System.Collections.Generic;

namespace Opiniones.Modelo
{
    public class Opinion
    {
        [BsonId]
        public string Id { get; set; }

        public string Recurso { get; set; }
        public IDictionary<String, Valoracion> Valoraciones { get; set; } = new Dictionary<String, Valoracion>();

        public int NumValoraciones { get {  return Valoraciones.Count;  } }

        public double CalificacionMedia { get {
            if (Valoraciones == null || Valoraciones.Count == 0) // Comprobación para evitar dividir entre 0
                return 0;
            var media = 0;
            foreach (var item in Valoraciones)
            {
                media += item.Value.Calificacion;
            }
            
            return media / Valoraciones.Count;
        } }
    }

    public class Valoracion {

        public String Correo { get; set; }
        
        public DateTime Fecha { get; set; }

        public int Calificacion { get; set; }

        public String Comentario { get; set; }
    }

    public class ResumenOpinion {
        public int numeroValoraciones { get; set; }

        public double calificacionMedia { get; set; }

        public string id {get; set; }
    }

    public class EventoValoracionCreada {
        public String idOpinion {get; set;}

        public Valoracion valoracion {get; set;}

        public ResumenOpinion resumenOpinion {get; set;}
    }

}
