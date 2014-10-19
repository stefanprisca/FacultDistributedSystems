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
            clientCookie = Request.Cookies.Get(Constants.COOKIE_UTYPE);
            if (clientCookie == null)
            {

                Response.Redirect("../Default");
            }
            if (!clientCookie.Value.Equals(Constants.ADMINISTRATOR_TYPE))
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
        }

        public void InsertUser(object sender, EventArgs e)
        {
            APPLICATIONUSER user = new APPLICATIONUSER();
            user.NAME = TextBox_INSERT_Name.Text;
            user.LOGINID = TextBox_INSERT_LoginId.Text;
            user.LOGINPW = TextBox_INSERT_LoginPW.Text;
            user.HOMEADDRESS = TextBox_INSERT_HomeAddr.Text;
            user.BIRTHDATE = TextBox_INSERT_Birthday.Text;
            user.LONGITUDE = TextBox_INSERT_Longitude.Text;
            user.LATITUDE = TextBox_INSERT_Latitude.Text;
            if (CheckBox_INSERT_UType.Checked)
            {
                user.TYPE = Constants.ADMINISTRATOR_TYPE;
            }
            else
            {
                user.TYPE = Constants.REGULAR_TYPE;
            }

            using (var db = new ds_assign1Entities())
            {
                db.APPLICATIONUSERs.Add(user);
                db.SaveChanges();
            }

            UserDataList.EditItemIndex = -1;
            UserDataList.DataBind();
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
        {//
            int productID = Convert.ToInt32(UserDataList.DataKeys[e.Item.ItemIndex]);
            using (var db = new ds_assign1Entities())
            {
                var user = db.APPLICATIONUSERs.Find(productID);
                if (user == null)
                {
                    return;
                }

                user.NAME = ((TextBox)e.Item.FindControl(Constants.TEXTBOX_EDIT_NAME)).Text;
                user.LOGINID = ((TextBox)e.Item.FindControl(Constants.TEXTBOX_EDIT_ID)).Text;
                user.HOMEADDRESS = ((TextBox)e.Item.FindControl(Constants.TEXTBOX_EDIT_HMADDR)).Text;
                user.BIRTHDATE = ((TextBox)e.Item.FindControl(Constants.TEXTBOX_EDIT_BDAY)).Text;
                user.LONGITUDE = ((TextBox)e.Item.FindControl(Constants.TEXTBOX_EDIT_LONG)).Text;
                user.LATITUDE = ((TextBox)e.Item.FindControl(Constants.TEXTBOX_EDIT_LAT)).Text;

                db.Entry(user).State = EntityState.Modified;
                db.SaveChanges();

            }



            UserDataList.EditItemIndex = -1;
            UserDataList.DataBind();
        }
        protected void UserDataList_DeleteCommand(object source, DataListCommandEventArgs e)
        {
            int productID = Convert.ToInt32(UserDataList.DataKeys[e.Item.ItemIndex]);
            using (var db = new ds_assign1Entities())
            {
                var user = db.APPLICATIONUSERs.Find(productID);
                if (user == null)
                {
                    return;
                }

                db.APPLICATIONUSERs.Remove(user);
                db.SaveChanges();
            }

            UserDataList.EditItemIndex = -1;
            UserDataList.DataBind();
        }
    }
}