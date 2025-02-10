using Repositorio;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;
using MongoDB.Bson;
using Opiniones.Modelo;
using System;

namespace Opiniones.Repositorio
{


    public class RepositorioOpinionesMongoDB : Repositorio<Opinion, string>
    {
        private readonly IMongoCollection<Opinion> opiniones;

        public RepositorioOpinionesMongoDB()
        {
            var client = new MongoClient(Environment.GetEnvironmentVariable("URI_MONGODB"));
            var database = client.GetDatabase("Proyecto-ARSO");

            opiniones = database.GetCollection<Opinion>("opiniones.net");
        }

        public string Add(Opinion entity)
        {
            opiniones.InsertOne(entity);

            return entity.Id;
        }

        public void Update(Opinion entity)
        {
            opiniones.ReplaceOne(opinion => opinion.Id == entity.Id, entity);
        }

        public void Delete(Opinion entity)
        {
            opiniones.DeleteOne(opinion => opinion.Id == entity.Id);
        }

        public List<Opinion> GetAll()
        {
            return opiniones.Find(_ => true).ToList();
        }

        public Opinion GetById(string id)
        {
            return opiniones
                .Find(opinion => opinion.Id == id)
                .FirstOrDefault();
        }

        public List<string> GetIds()
        {
            var todas =  opiniones.Find(_ => true).ToList();

            return todas.Select(p => p.Id).ToList();

        }
    }
}