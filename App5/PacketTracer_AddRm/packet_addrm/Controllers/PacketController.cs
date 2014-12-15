using packet_addrm.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace packet_addrm.Controllers
{
    public class PacketController : ApiController
    {
        public IEnumerable<Packet> GetPackets()
        {
            var db = new ds_assign5Entities();
            IQueryable<Packet> query = db.Packets;
            IEnumerable<Packet> pcks = query.AsEnumerable();
            if (!pcks.Any())
            {
                //PutPacket("New Pck");
                //PutPacket("New Pck2");
                //PutPacket("New Pck3");
                //PutPacket("New Pck4");
            }
            return pcks;
        }


        public HttpResponseMessage Get_AddPacket([FromUri] String Name, [FromUri] String Location)
        {
            var db = new ds_assign5Entities();
            Packet p = new Packet() { Name = Name.Trim(), Location = Location, EnableTracking = !String.IsNullOrEmpty(Location) };
            db.Packets.Add(p);
            db.SaveChanges();

            return Request.CreateResponse(HttpStatusCode.OK);
        }

        public void Delete_Packet([FromUri] int Id)
        {
            ds_assign5Entities db = new ds_assign5Entities();
            Packet p = db.Packets.Find(Id);
            if (p != null)
            {
                db.Packets.Remove(p);
                db.SaveChanges();
            }

        }

        // PUT api/<controller>/5
        public void Put_EnablePacket(int id)
        {
            ds_assign5Entities db = new ds_assign5Entities();
            Packet p = db.Packets.Find(id);
            p.EnableTracking = true;
            db.SaveChanges();

        }
    }
}
