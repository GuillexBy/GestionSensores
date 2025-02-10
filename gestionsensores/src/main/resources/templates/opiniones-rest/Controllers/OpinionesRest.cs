using Opiniones.Modelo;
using Opiniones.Servicio;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;

namespace OpinionesApi.Controllers
{

    public class OpinionRequest
    {
        public string Recurso { get; set; }
    }

    [Route("api/opiniones")]
    [ApiController]
    public class OpinionesController : ControllerBase
    {
        private readonly IServicioOpiniones _servicio;

        public OpinionesController(IServicioOpiniones servicio)
        {
            _servicio = servicio;
        }

        [HttpGet("{id}", Name = "GetOpinion")]
        public ActionResult<List<Valoracion>> Get(string id)
        {
            var entidad = _servicio.Get(id);

            if (entidad == null)
            {
                return NotFound();
            }
            List<Valoracion> valoraciones = new List<Valoracion>(entidad.Valoraciones.Values);
            return Ok(valoraciones);
            //return entidad;
        }

        [HttpPost]
        [Consumes("application/json")]
        public ActionResult<string> Create([FromBody] OpinionRequest recurso)
        {
            String id = _servicio.Create(recurso.Recurso);
            Console.WriteLine("id: "+ id);
            Console.WriteLine("recurso: " + recurso.Recurso);
            //return CreatedAtRoute("GetOpinion", new { id }, _servicio.Get(id));
            //Console.WriteLine("Aure: "+ Ok(id));
            return Ok(id);
        }

        [HttpPut("{id}")]
        public IActionResult addValoracion(string id, Valoracion valoracion)
        {
            Opinion opinion = _servicio.Get(id);
            if (opinion == null)
            {
                return NotFound();
            }

            if( String.IsNullOrEmpty(valoracion.Correo))
                return BadRequest();
            if( String.IsNullOrEmpty(valoracion.Fecha.ToString()))
                return BadRequest();
            if (valoracion.Calificacion>5 || valoracion.Calificacion<0)
                return BadRequest();

            _servicio.addValoracion(valoracion, id);

            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(string id)
        {
            var opinion = _servicio.Get(id);

            if (opinion == null)
                return NotFound();

            _servicio.Remove(id);

            return NoContent();
        }
    }
}
