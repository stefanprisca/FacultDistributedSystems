using app2.Content;
using app2.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace app2.Secured
{
    public partial class AdminPage : System.Web.UI.Page
    {
        private ds_assign1Entities db;
        private HttpCookie clientCookie;
        protected void Page_Load(object sender, EventArgs e)
        {
            db = new ds_assign1Entities();

            clientCookie = Request.Cookies.Get(Constants.COOKIE_NAME);
            if (clientCookie == null || !clientCookie.Value.Equals(Constants.ADMINISTRATOR_TYPE))
            {
                Response.Redirect("../Default");
            }
        }

        public IQueryable<APPLICATIONUSER> GetUsers()
        {
            return db.APPLICATIONUSERs;
        }

        public void LogOut(object sender, EventArgs e)
        {
            Request.Cookies.Clear();
            clientCookie.Value = null;
            Response.Cookies.Add(clientCookie);
            Response.Redirect("../Default");
            db.Dispose();
        }

    }
}