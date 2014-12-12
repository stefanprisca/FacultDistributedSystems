using packet_addrm.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
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
            if (! pcks.Any())
            {
                PutPacket("New Pck", "None");
            }
            return pcks;
        }


        public string PutPacket(String Name, String Location)
        {
            var db = new ds_assign5Entities();
            Packet p = new Packet() { Name = Name, Location = Location, EnableTracking = true };
            db.Packets.Add(p);
            db.SaveChanges();

            return "Added succesfully";
        }

        public void DeletePacket(int packetId)
        {
            using (ds_assign5Entities db = new ds_assign5Entities())
            {
                Packet p = db.Packets.Find(packetId);
                db.Packets.Remove(p);
                db.SaveChanges();
            };
        }
    }
}
