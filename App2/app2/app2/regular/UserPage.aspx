<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="UserPage.aspx.cs" Inherits="app2.regular.UserPage" %>

<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <script type="text/javascript" src="../Scripts/MyScripts.js"></script>
    <asp:HiddenField ClientIDMode="Static" ID="longitude" runat="server" Value="<%#getUser().LONGITUDE %>" />
    <asp:HiddenField ClientIDMode="Static" ID="latitude" runat="server" Value="<%#getUser().LATITUDE %>" />
    <ul>
        <li>
            <label>Name: <%#getUser().NAME %></label>
        </li>
        <li>
            <label>Login ID: <%#getUser().LOGINID %></label>
        </li>
        <li>
            <label>Home Address: <%#getUser().HOMEADDRESS %></label>
        </li>
        <li>
            <label>Birthday: <%#getUser().BIRTHDATE %></label>
        </li>
        <li>
            <label>Global Position: (<%#getUser().LATITUDE %> , <%#getUser().LONGITUDE %> )</label>
        </li>
        <li>
            <label>User Type: <%#getUser().TYPE %></label>
        </li>
    </ul>
    <hr />
    <asp:LinkButton ID="tmzBtn"
        runat="server"
        OnClientClick="checkLocation()"
        Text="Check" />
    <label>your global timezone based on the Google Maps API</label>
    <hr />
    <br /><br /><br />
    <asp:Button ID="LogoutButton" runat="server"
        OnClick="LogoutButton_Click" Text="Log Out"/>
</asp:Content>
