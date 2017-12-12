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
        optimizeWaypoints: true,
        travelMode: 'DRIVING'
    }, function(response, status) {
        if (status === 'OK') {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            var summaryPanel = document.getElementById('directions-panel');
            summaryPanel.innerHTML = '';
            // For each route, display summary information.
            for (var i = 0; i < route.legs.length; i++) {
                var routeSegment = i + 1;
                summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment +
                    '</b><br>';
                summaryPanel.innerHTML += route.legs[i].start_address.replace(", Schweiz", " ") + ' nach ';
                summaryPanel.innerHTML += route.legs[i].end_address.replace(", Schweiz", " ") + '<br>';
                summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
            }
        } else {
            if (status == 'ZERO_RESULTS') {
                window.alert('No route could be found between the origin and destination.');
            } else if (status == 'UNKNOWN_ERROR') {
                window.alert('A directions request could not be processed due to a server error. The request may succeed if you try again.');
            } else if (status == 'REQUEST_DENIED') {
                window.alert('This webpage is not allowed to use the directions service.');
            } else if (status == 'OVER_QUERY_LIMIT') {
                widnow.alert('The webpage has gone over the requests limit in too short a period of time.');
            } else if (status == 'NOT_FOUND') {
                window.alert('At least one of the origin, destination, or waypoints could not be geocoded.');
            } else if (status == 'INVALID_REQUEST') {
                window.alert('The DirectionsRequest provided was invalid.');
            }else{
                window.alert('Directions request failed due to ' + status);
            }
        }
    });
}