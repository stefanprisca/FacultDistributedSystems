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
        public static readonly String COOKIE_NAME = "clientCookie";
	    public static readonly String ADMINISTRATOR_TYPE = "user.administrator";
	    public static readonly String REGULAR_TYPE = "user.regular";

        private ds_assign1Entities db;

        protected void Page_Load(object sender, EventArgs e)
        {
            db = new ds_assign1Entities();
        }



        protected void LogIn(object sender, EventArgs e)
        {
            var users = db.APPLICATIONUSERs;
            var reqId = LoginID.Text;
            var reqPw = LoginPW.Text;
            var user = users.Single(u => u.LOGINID.Equals(reqId) && u.LOGINPW.Equals(reqPw));
            if (user != null)
            {
                HttpCookie userCookie = new HttpCookie(COOKIE_NAME);
                userCookie.Value = user.TYPE;
                userCookie.Expires = DateTime.Now.AddDays(1);

                Response.Cookies.Add(userCookie);

                if(user.TYPE.Equals(ADMINISTRATOR_TYPE)){
                    Response.Redirect("secured/AdminPage");
                }
                else { }
            }
        }

    }
}