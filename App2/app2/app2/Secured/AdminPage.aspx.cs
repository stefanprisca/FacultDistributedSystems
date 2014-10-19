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
                LogOut(sender, e);
            }

        }

        public IQueryable<APPLICATIONUSER> GetUsers()
        {
            if (db == null)
            {
                db = new ds_assign1Entities();
            }
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



        protected void UserDataList_EditCommand(object source, DataListCommandEventArgs e)
        { 
            UserDataList.EditItemIndex = e.Item.ItemIndex; 
            UserDataList.DataBind(); 
        }

        protected void UserDataList_CancelCommand(object source, DataListCommandEventArgs e)
        {
            UserDataList.EditItemIndex = -1;
            UserDataList.DataBind();
        }

        protected void UserDataList_UpdateCommand(object source, DataListCommandEventArgs e)
        {
           //todo
        }
        protected void UserDataList_DeleteCommand(object source, DataListCommandEventArgs e)
        {
            //todo
        }
    }
}