using packet_addrm.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace packet_addrm.Controllers
{
    public class TrackingController : ApiController
    {
        
        // GET api/<controller>/5
        public string Get(int id)
        {
            ds_assign5Entities db = new ds_assign5Entities();
            Packet p = db.Packets.Find(id);
            return p.Location;
        }

        public void GET_UpdateLocation([FromUri]int Id, [FromUri]string Location)
        {
            ds_assign5Entities db = new ds_assign5Entities();
            Packet p = db.Packets.Find(Id);
            p.Location = Location.Trim();
            db.SaveChanges();
        }

    }
}