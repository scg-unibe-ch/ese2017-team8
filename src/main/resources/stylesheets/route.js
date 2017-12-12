var mp;
var mk;

function toggleView(){
    var e = document.getElementById("route");
    var f = document.getElementById("parceloverview");
    var g = document.getElementById("showparcels");
    var h = document.getElementById("showroute");

    if(e.style.height == 'auto') {
        e.style.height = '0px';
        f.style.height = 'auto';
        g.style.display = 'none';
        h.style.display = 'block';
    } else {
        e.style.height = 'auto';
        f.style.height = '0px';
        g.style.display = 'block';
        h.style.display = 'none';
    }
}

function initMap() {
    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer;

    var mapProp = {
        center: {lat: 46.9479739, lng: 7.447446799999966},
        zoom: 8,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var marker = new google.maps.Marker({
        position: {lat: 46.9479739, lng: 7.447446799999966},
        title: 'Zentrale'
    });
    mk = marker;

    var map = new google.maps.Map(document.getElementById('map'), mapProp);
    mp = map;

    directionsDisplay.setMap(map);

    window.onload = function () {

        calculateAndDisplayRoute(directionsService, directionsDisplay);
    };
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {

    var waypts = [];
    var table = document.getElementById("driverOverview");
    var row = table.rows;

    var rowtext;
    for (var i = 1; i < row.length; i++) {
        // var rowText = rows[i].cells[3].innerHTML + "," + rows.cells[4].innerHTML + "" + rows.cells[5].innerHTML + "CH";
        rowtext = row[i].cells[5].innerHTML + "," + row[i].cells[6].innerHTML + " " + row[i].cells[7].innerHTML + ", CH";
        waypts.push({
            location: rowtext,
            stopover: true
        });

    }

    /* //test waypoint working
    waypts.push({
         location: "Luzern, CH",
         stopover: true
     });*/

    directionsService.route({

        origin: {lat: 46.9479739, lng: 7.447446799999966},
        destination: {lat: 46.9479739, lng: 7.447446799999966},
        waypoints: waypts,
        optimizeWaypoints: false,
        travelMode: 'DRIVING'
    }, function(response, status) {
        var summaryPanel = document.getElementById('directions-panel');
        var exactNavi = document.getElementById('navi-panel');
        if (status === 'OK') {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            summaryPanel.innerHTML = '';
            exactNavi.innerHTML = '';
            // For each route, display summary information.
            for (var i = 0; i < route.legs.length; i++) {

                    exactNavi.innerHTML += '<br>' + (i+1) + ". Paket nach: " + route.legs[i].end_address.replace(", Schweiz", " ") + '<br>';
                    for(var j = 0; j < route.legs[i].steps.length; j++){
                        exactNavi.innerHTML += route.legs[i].steps[j].instructions + '<br>';
                        exactNavi.innerHTML += "für " + (route.legs[i].steps[j].distance.value) + " Meter folgen" + '<br>';

                    }

                    exactNavi.innerHTML += '<br>';

                var routeSegment ='ABCDEFGHIJKLMNOPQRSTUVWXYZ'.charAt(i+1);
                if (i == route.legs.length-1){
                    summaryPanel.innerHTML += '<strong>Route in den Feierabend</strong><br>';
                } else {
                    summaryPanel.innerHTML += '<strong>Route nach Ziel ' + routeSegment + '</strong><br>';
                }
                summaryPanel.innerHTML += route.legs[i].start_address.replace(", Schweiz", " ") + ' nach ';
                summaryPanel.innerHTML += route.legs[i].end_address.replace(", Schweiz", " ") + '<br>';
                summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
            }
        } else {
            summaryPanel.innerHTML += '<strong>Fehler!</strong><br />'
            if (status == 'ZERO_RESULTS') {
                summaryPanel.innerHTML += 'Es konnte keine Route gefunden werden.';
            } else if (status == 'UNKNOWN_ERROR') {
                summaryPanel.innerHTML += 'Aufgrund eines Serverporblems konnte keine Route berechnet werden. Versuchen sie die Seite neu zu laden.';
            } else if (status == 'REQUEST_DENIED') {
                summaryPanel.innerHTML += 'Diese Seite verbietet den gebrauch vom Routen Service';
            } else if (status == 'OVER_QUERY_LIMIT') {
                summaryPanel.innerHTML += 'The webpage has gone over the requests limit in too short a period of time.';
            } else if (status == 'NOT_FOUND') {
                summaryPanel.innerHTML += 'At least one of the origin, destination, or waypoints could not be geocoded.';
            } else if (status == 'INVALID_REQUEST') {
                summaryPanel.innerHTML += 'The DirectionsRequest provided was invalid.';
            }else{
                summaryPanel.innerHTML += 'Directions request failed due to ' + status;
            }
        }
    });
}