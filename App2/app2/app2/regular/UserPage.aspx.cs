using app2.Content;
using app2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace app2.regular
{
    public partial class UserPage : System.Web.UI.Page
    {

        private APPLICATIONUSER AppUser;


        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie clientCookie = Request.Cookies.Get(Constants.COOKIE_UTYPE);
            HttpCookie utypeCookie = Request.Cookies[Constants.COOKIE_UID];
            if (clientCookie == null || utypeCookie == null)
            {
                Response.Redirect("../Default");
            }
            if (!clientCookie.Value.Equals(Constants.REGULAR_TYPE))
            {
                LogoutButton_Click(sender, e);
            }
            
            decimal id = Decimal.Parse(utypeCookie.Value);

            using (var db = new ds_assign1Entities())
            {
                AppUser = db.APPLICATIONUSERs.Find(id);
            }

            if (AppUser == null)
            {
                LogoutButton_Click(sender, e);
            }
            Page.DataBind();
            ClientScript.RegisterHiddenField("latitude", AppUser.LATITUDE);
            ClientScript.RegisterHiddenField("longitude", AppUser.LONGITUDE);
        }


        public APPLICATIONUSER getUser()
        {
            return AppUser;
        }

        protected void LogoutButton_Click(object sender, EventArgs e)
        {
            Response.Cookies[Constants.COOKIE_UID].Value = null;
            Response.Cookies[Constants.COOKIE_UTYPE].Value = null;
            Response.Redirect("../Default");
        }
    }
}