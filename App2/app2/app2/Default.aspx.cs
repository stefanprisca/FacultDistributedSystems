using app2.Content;
using app2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace app2
{
    public partial class _Default : Page
    {


        private ds_assign1Entities db;

        protected void Page_Load(object sender, EventArgs e)
        {

        }



        protected void LogIn(object sender, EventArgs e)
        {
            db = new ds_assign1Entities();
            var users = db.APPLICATIONUSERs;
            var reqId = LoginID.Text;
            var reqPw = LoginPW.Text;
            try
            {
                var user = users.First(u => u.LOGINID.Equals(reqId) && u.LOGINPW.Equals(reqPw));
                if (user != null)
                {
                    HttpCookie userCookie = new HttpCookie(Constants.COOKIE_UTYPE);
                    userCookie.Value = user.TYPE;
                    userCookie.Expires = DateTime.Now.AddDays(1);

                    Response.Cookies.Add(userCookie);

                    if (user.TYPE.Equals(Constants.ADMINISTRATOR_TYPE))
                    {
                        Response.Redirect("secured/AdminPage");
                    }
                    else
                    {
                        HttpCookie userIdCookie = new HttpCookie(Constants.COOKIE_UID);
                        userIdCookie.Value = user.ID.ToString();
                        userIdCookie.Expires = DateTime.Now.AddDays(1);
                        Response.Cookies.Add(userIdCookie);
                        Response.Redirect("regular/UserPage");
                    }
                }
            }
            catch (Exception)
            { }


            db.Dispose();
        }

    }
}