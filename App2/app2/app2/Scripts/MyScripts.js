var baseUrl = "https://maps.googleapis.com/maps/api/timezone/xml?";
var timestamp = 1331161200;
function checkLocation() {

    longitude = document.getElementById("longitude").value;
    latitude = document.getElementById("latitude").value;
    url = baseUrl + "location=" + latitude + "," + longitude + "&timestamp="+timestamp;

    text = httpGet(url);
    alert(text);
}

function httpGet(theUrl) {
    var xmlHttp = null;
    xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, false);
    xmlHttp.send(null);
    xmlDoc = xmlHttp.responseXML;

    date = new Date(timestamp + parseInt(xmlDoc.getElementsByTagName("raw_offset")[0].childNodes[0].nodeValue) + parseInt(xmlDoc.getElementsByTagName("dst_offset")[0].childNodes[0].nodeValue));

    var prettyText = "Current Position:\n";
    prettyText += "Retrieve Status: " + xmlDoc.getElementsByTagName("status")[0].childNodes[0].nodeValue + "\n";
    prettyText += "Local Time: " + date + "\n";
    prettyText += "Timezone: " + xmlDoc.getElementsByTagName("time_zone_name")[0].childNodes[0].nodeValue + "(ID: " + xmlDoc.getElementsByTagName("time_zone_id")[0].childNodes[0].nodeValue + ")";

    return prettyText;
}