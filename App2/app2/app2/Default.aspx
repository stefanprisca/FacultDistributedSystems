<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="app2._Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <div class="row">

        <h2>Log In</h2>
        <div class="table-responsive input-group input-group-addon">
            <div>
                <div class="input-lg ">
                    <asp:Label runat="server" AssociatedControlID="LoginID">Login ID</asp:Label>

                    <asp:TextBox runat="server" ID="LoginID" />

                </div>
            </div>
            <div>
                <div class="input-lg">
                    <asp:Label runat="server" AssociatedControlID="LoginPW">Login PW</asp:Label>
                    <asp:TextBox runat="server" ID="LoginPW" TextMode="Password"/>
                </div>
            </div>
            <asp:button runat="server" OnClick="LogIn" Text="Log In"/>
        </div>

    </div>

</asp:Content>
