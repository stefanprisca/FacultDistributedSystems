<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="AdminPage.aspx.cs" Inherits="app2.Secured.AdminPage" %>

<asp:Content runat="server"  ID="BodyContent" ContentPlaceHolderID="MainContent">
    <div class="row">
     <asp:ListView ID="userList"
                
                ItemType="app2.Models.APPLICATIONUSER"
                runat="server"
                GroupItemCount="4"
                SelectMethod="GetUsers"
                >
                <EmptyDataTemplate>
                    <table >
                        <tr>
                            <td>No data was returned.</td>
                        </tr>
                    </table>
                </EmptyDataTemplate>
                <EmptyItemTemplate>
                    <td/>
                </EmptyItemTemplate>
                <GroupTemplate>
                    <tr id="itemPlaceholderContainer" runat="server">
                        <td id="itemPlaceholder" runat="server"></td>
                    </tr>
                </GroupTemplate>
                <ItemTemplate>
                    <td runat="server">
                        <table runat="server">
                            
                            <tr>
                                <td> <span style="font-size:30px;"> <%#Item.NAME %></span></td>
                                </tr>
                            <tr>
                                <td> <a href="UserEdit.aspx?<%#app2.Content.Constants.QUERRY_UID%>=<%#Item.ID %>">View and Edit user </a></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </ItemTemplate>
                <LayoutTemplate>
                    <table style="width:100%;">
                        <tbody>
                            <tr>
                                <td>
                                    <table id="groupPlaceholderContainer" runat="server" style="width:100%">
                                        <tr id="groupPlaceholder"></tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                            </tr>
                            <tr></tr>
                        </tbody>
                    </table>
                </LayoutTemplate>
            </asp:ListView>
        <asp:button runat="server" OnClick="LogOut" Text="Log Out"/>
    </div>
</asp:Content>
