var map = L.map( 'map', {
  center: [20.0, 5.0],
  minZoom: 6,
  zoom: 6
})

L.tileLayer( 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>',
  subdomains: ['a', 'b', 'c']
}).addTo( map )

var myURL = jQuery( 'script[src$="custom_leaflet.js"]' ).attr( 'src' ).replace( 'custom_leaflet.js', '' )

var myIcon = L.icon({
  iconUrl: myURL + 'images/pin24.png',
  iconRetinaUrl: myURL + 'images/pin48.png',
  iconSize: [29, 24],
  iconAnchor: [9, 21],
  popupAnchor: [0, -14]
})

var latlngs = Array();

for ( var i=0; i < markers.length; ++i )
{
 var mark_1 = L.marker( [markers[i].lat, markers[i].lng], {icon: myIcon} )
  .bindPopup( markers[i].name )
  .addTo( map );
  latlngs.push(mark_1.getLatLng());
}

var polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);
map.fitBounds(polyline.getBounds());