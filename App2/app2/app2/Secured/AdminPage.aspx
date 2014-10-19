<%@ Page EnableEventValidation="false" Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="AdminPage.aspx.cs" Inherits="app2.Secured.AdminPage" %>

<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <div class="row" style="height: 610px">
        <asp:DataList ID="UserDataList"
            ItemType="app2.Models.APPLICATIONUSER"
            runat="server"
            SelectMethod="" Height="50px" Width="1110px"
            DataSourceID="UsersDataSource" GridLines="Both" HorizontalAlign="Justify" RepeatColumns="5"
            OnEditCommand="UserDataList_EditCommand"
            OnCancelCommand="UserDataList_CancelCommand"
            OnUpdateCommand="UserDataList_UpdateCommand"
            OnDeleteCommand="UserDataList_DeleteCommand">

            <EditItemTemplate>
                Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox1" runat="server" Text='<%# Eval("NAME", "{0}") %>'></asp:TextBox>
                <br />
                LoginID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox2" runat="server" Text='<%# Eval("LOGINID", "{0}") %>'></asp:TextBox>
                <br />
                LoginPw:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox3" runat="server" Text='<%# Eval("LOGINPW") %>'></asp:TextBox>
                <br />
                Birthday:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox4" runat="server" Text='<%# Eval("BIRTHDATE", "{0}") %>'></asp:TextBox>
                <br />
                Longitude:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox5" runat="server" Text='<%# Eval("LONGITUDE", "{0}") %>'></asp:TextBox>
                <br />
                Latitude:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox6" runat="server" Text='<%# Eval("LATITUDE", "{0}") %>'></asp:TextBox>
                <br />
                <br />
                <div style="text-align: center; font-size: small">
                    <asp:LinkButton ID="Update" runat="server" Text="Update" CommandName="Update" />
                </div>
                <hr />
                <div style="text-align: center; font-size: small">
                    <asp:LinkButton ID="Cancel" runat="server" CommandName="Cancel" Text="Cancel" />
                </div>
            </EditItemTemplate>
            <ItemStyle BorderColor="#333300" BorderStyle="Solid" />

            <ItemTemplate>
                <asp:Label Font-Size="Large" Font-Bold="true" Text='<%# Eval("NAME") %>' runat="server" ID="NAMELabel" /><br />

                BIRTHDATE:
                <asp:Label Text='<%# Eval("BIRTHDATE") %>' runat="server" ID="BIRTHDATELabel" /><br />
                HOMEADDRESS:
                <asp:Label Text='<%# Eval("HOMEADDRESS") %>' runat="server" ID="HOMEADDRESSLabel" /><br />
                LOGINID:
                <asp:Label Text='<%# Eval("LOGINID") %>' runat="server" ID="LOGINIDLabel" /><br />
                LATITUDE:
                <asp:Label Text='<%# Eval("LATITUDE") %>' runat="server" ID="LATITUDELabel" /><br />
                LONGITUDE:
                <asp:Label Text='<%# Eval("LONGITUDE") %>' runat="server" ID="LONGITUDELabel" /><br />
                TYPE:
                <asp:Label Text='<%# Eval("TYPE") %>' runat="server" ID="TYPELabel" />
                <br />
                <br />
                <div style="text-align: center; font-size: small">
                    <asp:LinkButton ID="EditButton"
                        Text="Edit"
                        CommandName="Edit"
                        runat="server" />
                </div>
                <hr />
                <div style="text-align: center; font-size: small">
                    <asp:LinkButton ID="DeleteButton"
                        Text="Delete"
                        CommandName="Delete"
                        runat="server" />
                </div>
                <br />

            </ItemTemplate>



            <SelectedItemStyle BackColor="#FF9900" />
            <SeparatorStyle BorderColor="#660033" BorderStyle="Double" />



        </asp:DataList>
        <asp:ObjectDataSource runat="server" ID="UsersDataSource" SelectMethod="GetUsers" TypeName="app2.Secured.AdminPage"></asp:ObjectDataSource>
        <asp:Button runat="server" OnClick="LogOut" Text="Log Out" />
    </div>
</asp:Content>
