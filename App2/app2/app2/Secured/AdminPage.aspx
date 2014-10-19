<%@ Page EnableEventValidation="false" Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="AdminPage.aspx.cs" Inherits="app2.Secured.AdminPage" %>

<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <div class="row" style="height: 610px">
        <asp:DataList ID="UserDataList"
            ItemType="app2.Models.APPLICATIONUSER"
            runat="server"
            SelectMethod="" Height="50px" Width="1110px"
            DataSourceID="UsersDataSource"
            DataKeyField="ID"
            GridLines="Both" HorizontalAlign="Justify" RepeatColumns="5"
            OnEditCommand="UserDataList_EditCommand"
            OnCancelCommand="UserDataList_CancelCommand"
            OnUpdateCommand="UserDataList_UpdateCommand"
            OnDeleteCommand="UserDataList_DeleteCommand">

            <EditItemTemplate>
                Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_Edit_Name" runat="server" Text='<%# Eval("NAME") %>'></asp:TextBox>
                <br />
                LoginID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_Edit_LoginId" runat="server" Text='<%# Eval("LOGINID") %>'></asp:TextBox>
                <br />
                Home Address:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_Edit_HomeAddr" runat="server" Text='<%# Eval("HOMEADDRESS") %>'></asp:TextBox>
                <br />
                Birthday:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_Edit_Birthday" runat="server" Text='<%# Eval("BIRTHDATE") %>'></asp:TextBox>
                <br />
                Longitude:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_Edit_Longitude" runat="server" Text='<%# Eval("LONGITUDE") %>'></asp:TextBox>
                <br />
                Latitude:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_Edit_Latitude" runat="server" Text='<%# Eval("LATITUDE") %>'></asp:TextBox>
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
        <hr />
        <div>
            <h2>Add User</h2>
            <hr />
            Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_INSERT_Name" runat="server"></asp:TextBox>
            <br />
            LoginID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_INSERT_LoginId" runat="server"></asp:TextBox>
            <br />
            LoginID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_INSERT_LoginPW" TextMode="Password" runat="server"></asp:TextBox>
            <br />
            Home Address:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:TextBox ID="TextBox_INSERT_HomeAddr" runat="server"></asp:TextBox>
            <br />
            Birthday:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_INSERT_Birthday" runat="server"></asp:TextBox>
            <br />
            Longitude:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_INSERT_Longitude" runat="server"></asp:TextBox>
            <br />
            Latitude:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:TextBox ID="TextBox_INSERT_Latitude" runat="server"></asp:TextBox>
            <br />
            Admin Type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:CheckBox ID="CheckBox_INSERT_UType" runat="server"></asp:CheckBox>
            <br />
            <br />
            <div style="text-align: left; font-size: small">
                <asp:Button runat="server" type="button" OnClick="InsertUser" Text="Save"/>
            </div>
            <br />
        </div>
        <hr />
        <asp:ObjectDataSource runat="server" ID="UsersDataSource" SelectMethod="GetUsers" TypeName="app2.Secured.AdminPage"></asp:ObjectDataSource>
        <asp:Button runat="server" OnClick="LogOut" Text="Log Out" />
    </div>
            <hr />
            </asp:Content>
