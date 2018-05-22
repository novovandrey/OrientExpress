$(function() {
    $('.collapsible').collapsible();
    $('select').formSelect();

    $('div[class^="div"]').on('click', function () {
        event.stopPropagation();
    });

    $('div.fixed-action-btn').on('click', function () {
        var existRowAdditive = $('#modal').html();
        if (existRowAdditive) return false;
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/initTrainroute",
            success:function(data) {
                $('#modal').html( data );
                $('#modal').show("slow","swing");
            }
        });

    });

});

// $.ajax({
//     type: "POST",
//     url: "http://localhost:8080/trainroute/add",
//     success:function(data) {
//         M.toast({html: 'Add row success!'})
//         // var el = "#headrow"+elementid;
//         // $(el).remove();
//         // el = "#headdetails"+elementid;
//         // $(el).remove();
//     }
// });

// var $TABLE = $('#table');
// //var $BTN = $('#export-btn');
// //var $EXPORT = $('#export');
//
// $('.table-add').click(function () {
//     var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
//     $TABLE.find('table').append($clone);
// });
//
// $('.table-remove').click(function () {
//     $(this).parents('tr').detach();
// });

function doEditable(elementid) {

    var btn = "#btn"+elementid;

    $('#trainroute tr').attr('contenteditable','false');
    $('button[id^="btn"]').each(function() {
        if('#'+this.id!==btn) $(this).addClass('disabled')
    });

    var el = "#row"+elementid;
    //$(el).attr('contenteditable','true');



    if($(btn).hasClass('disabled')) {
        $(el).attr('contenteditable','true');
        $('tr'+el + '> td').each(function() {
            if (this.className=="fromst") $(this).addClass('borderpx');
            if (this.className=="tost") $(this).addClass('borderpx');
            if (this.className=="interval") $(this).addClass('borderpx');
        });
        $(btn).removeClass('disabled');
    }
    else{
        $(el).attr('contenteditable','false');
        $('tr'+el + '> td').each(function() {
            if (this.className=="fromst") $(this).removeClass('borderpx');
            if (this.className=="tost") $(this).removeClass('borderpx');
            if (this.className=="interval") $(this).removeClass('borderpx');
        });
        $(btn).addClass('disabled');
    }
}

function deleteItem(elementid) {
    if (confirm("Are you sure?")) {
        var link = "/schedule/"+elementid+"/delete";
        var trainData={};
        $.ajax({
            type: "POST",
            url: "http://localhost:8080"+link,
            success:function(data) {
                M.toast({html: 'Delete row success!'})
                var el = "#row"+elementid;
                $(el).remove();
            }
        });
    }
    return false;
}

function saveItem(elementid) {
    if (confirm("Are you sure save?")) {
        var link = "/schedule/"+elementid+"/save";
        var el = "#row"+elementid;
        var routeData={};
       routeData["fromst"] = $(".fromst"+elementid).text()
       routeData["tost"] = $(".tost"+elementid).text()
       routeData["interval"] = $(".interval"+elementid).text()

        datareq= JSON.stringify( routeData );
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080"+link,
            data: datareq,
            success:function(data) {
                M.toast({html: 'Save row success!'})
                $('#trainroute tr').attr('contenteditable','false');
                var btn = "#btn"+elementid;
                $(btn).addClass('disabled');
            },
            error:function(data) {
                alert("error");
            }

        });
    }
    return false;
}

//trainroute
function doEditableTR(elementid) {

    event.stopPropagation();

    $('div[class^="div"]').each(function() {
        $(this).attr('contenteditable','false');
        $(this).removeClass('borderpx')
    });

    var btn = "#btnTR"+elementid;
    $('button[id^="btnTR"]').each(function() {
        if('#'+this.id!==btn) $(this).addClass('disabled')
    });

    var el = ".div"+elementid;

    if($(btn).hasClass('disabled')) {
        $(el).attr('contenteditable','true');
        $(el).addClass('borderpx')
        $(btn).removeClass('disabled');
    }
    else{
        $(el).attr('contenteditable','false');
        $(el).removeClass('borderpx')
        $(btn).addClass('disabled');
    }
}

function deleteItemTR(elementid) {
    event.stopPropagation();
    if (confirm("Are you sure delete trainroute and it details?")) {
        var link = "/trainroute/"+elementid+"/delete";
        var trainData={};
        $.ajax({
            type: "POST",
            url: "http://localhost:8080"+link,
            success:function(data) {
                M.toast({html: 'Delete row success!'})
                var el = "#headrow"+elementid;
                $(el).remove();
                el = "#headdetails"+elementid;
                $(el).remove();
            }
        });
    }
    return false;
}

//not used
function saveItemTR(elementid) {
    event.stopPropagation();
    if (confirm("Are you sure save?")) {
        var link = "/trainroute/"+elementid+"/save";
        var routeData={};
        routeData["traincode"] = $("#traincode"+elementid).text()
        var tt=$("#traincode"+elementid).text()

        routeData["arrst"] = $("#arrst"+elementid).text()
        routeData["depst"] = $("#depst"+elementid).text()
        datareq= JSON.stringify( routeData );
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080"+link,
            data: datareq,
            success:function(data) {
                M.toast({html: 'Save route success!'})
                var btn = "#btnTR"+elementid;
                if($(btn).hasClass('disabled')) {
                    $(el).attr('contenteditable','true');
                    $(el).addClass('borderpx')
                    $(btn).removeClass('disabled');
                }
                else{
                    $(el).attr('contenteditable','false');
                    $(el).removeClass('borderpx')
                    $(btn).addClass('disabled');
                }
            },
            error:function(data) {
                alert("error");
            }

        });
    }
    return false;
}

function saveItemNew() {
    event.stopPropagation();
    if (confirm("Are you sure?")) {
        var routeData={};
        routeData["traincode"] = $("#traincode_new").val();
        routeData["arrst"] = $("#arrst_new").val();
        routeData["depst"] = $("#depst_new").val();
        datareq= JSON.stringify( routeData );
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/trainroute/add",
            data: datareq,
            success:function(data) {
                $('#modal').html("");
                //$( ".hoverable:last" ).append( data);
                $( data ).appendTo( $( ".collapsible" ) );
                M.toast({html: 'Save route success!'})
            },
            error:function(data) {
                alert("error");
            }

        });
    }
    return false;
}