function getPage(page) {
    var input = document.getElementById("pageHiddenInput");
    input.value = page;
}

function navigationBtnFunc(action) {
    var actionBtn = action;
    var input = document.getElementById("navigationBtnInput");
    input.value = actionBtn;
}

function init_map() {
    var myOptions = {
        zoom: 16,
        center: new google.maps.LatLng(53.90453979999999, 27.561524400000053),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);
    var marker = new google.maps.Marker({
        map: map,
        position: new google.maps.LatLng(53.90453979999999, 27.561524400000053)
    });
    var infowindow = new google.maps.InfoWindow(
        {
            content: '<strong>Office "iPayWebApplication", Kalinovskogo 31</strong><br>Belarus, Minsk<br>'
        }
    );
    google.maps.event.addListener(marker, 'click', function () {
        infowindow.open(map, marker);
    });
    infowindow.open(map, marker);
}

google.maps.event.addDomListener(window, 'load', init_map);

