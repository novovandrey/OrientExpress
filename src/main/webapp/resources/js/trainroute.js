$(function() {
    $('.collapsible').collapsible();
    $('select').formSelect();
    $('.tooltipped').tooltip();

    $('div[class^="div"]').on('click', function () {
        event.stopPropagation();
    });

    $('#btnTRNew').on('click', function () {
        $('#float-btn').removeClass('grey').addClass('red');
    });

    $('div.fixed-action-btn').on('click', function () {
        var existRowAdditive = $('#modal').html();
        if (existRowAdditive) return false;
        $.ajax({
            type: "POST",
            url: "http://localhost:8081/initTrainroute",
            success:function(data) {
                $('#modal').html( data );
                $('#modal').show("slow","swing");
                // $('#float-btn').removeClass('red').addClass('grey');
            }
        });

    });
    $("button[id^=addItem]").on('click', function () {
        var existTrainScheduleAdditive = $('#newItem').html();
        if (existTrainScheduleAdditive) return false;
        var traincode = $(this).attr('id');
        traincode = traincode.replace("addItem", "");
        var newItemdiv= '#newItem'+traincode;
        $.ajax({
            type: "POST",
            url: "http://localhost:8081/initTrainSchedule/"+traincode,
            success:function(data) {
                $(newItemdiv).html( data );
                $(newItemdiv).show("slow","swing");
                // $('#float-btn').removeClass('red').addClass('grey');
            }
        });

    });

});

function doEditable(elementid) {

    var btn = "#btn"+elementid;

    //$('#trainroute tr').attr('contenteditable','false');
    $('.interval'+elementid).attr('contenteditable','false');
    $('.interval'+elementid).removeClass('tooltipped');
    $('button[id^="btn"]').each(function() {
        if('#'+this.id!==btn) $(this).addClass('disabled');
    });

    var el = "#row"+elementid;
    //$(el).attr('contenteditable','true');

    if($(btn).hasClass('disabled')) {
        $('.interval'+elementid).attr('contenteditable','true');
        $('.interval'+elementid).addClass('tooltipped'+elementid);
        $('.tooltipped'+elementid).tooltip();
        $('.interval'+elementid).addClass('borderpx');
        $(btn).removeClass('disabled');
        $('.interval'+elementid).focus();
    }
    else{
        $('.interval'+elementid).attr('contenteditable','false');
        var elem = document.querySelector('.tooltipped'+elementid);
        var instance = M.Tooltip.getInstance(elem);
        instance.destroy();
        $('.interval'+elementid).removeClass('tooltipped'+elementid);
        $('.interval'+elementid).removeClass('borderpx');

        $(btn).addClass('disabled');
    }
}

function deleteItem(elementid) {

    swal({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.value) {

        var link = "/schedule/"+elementid+"/delete";
        var trainData={};
        $.ajax({
            type: "POST",
            url: "http://localhost:8081"+link,
            success:function(data) {
                M.toast({html: 'Delete row success!'})
                var el = "#row"+elementid;
                $(el).remove();
            }
        });

    }
})

    return false;
}

function saveItem(elementid) {

    swal({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, save it!'
    }).then((result) => {
        if (result.value) {

        var link = "/schedule/"+elementid+"/save";
        var el = "#row"+elementid;
        var routeData={};
        routeData["fromst"] = $(".fromst"+elementid).text();
        routeData["tost"] = $(".tost"+elementid).text();
        routeData["interval"] = $(".interval"+elementid).text();

        datareq= JSON.stringify( routeData );
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8081"+link,
            data: datareq,
            success:function(data) {
                M.toast({html: 'Save row success!'});
                $('.interval'+elementid).attr('contenteditable','false');
                var elem = document.querySelector('.tooltipped'+elementid);
                var instance = M.Tooltip.getInstance(elem);
                instance.destroy();
                var btn = "#btn"+elementid;
                $(btn).addClass('disabled');
                $('.interval'+elementid).removeClass('borderpx');
            },
            error:function(data) {
                alert("error");
            }

        });

    }
})
    return false;
}


function saveTrainScheduleItemNew() {
    event.stopPropagation();

    swal({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, save it!'
    }).then((result) => {
        if (result.value) {

        var datareq={};
        datareq["arrst"] = $("#arrst_new").val();
        datareq["depst"] = $("#depst_new").val();
        datareq["interval"] = $("#timeInterval_new").val();
        var traincodeNew = $("tr[id^=rowNew]").attr('id');
        traincodeNew = traincodeNew.replace("rowNew","");
        datareq["traincode"] = traincodeNew;
        var tableID = "#trainroute"+traincodeNew;
        var newItemdiv= '#newItem'+traincodeNew;
        //return false;
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "http://localhost:8081/schedule/add",
            data: datareq,
            success:function(data) {
                $(newItemdiv).html("");
                $(tableID + ' > tbody:last-child').append(data);
                //$( data ).appendTo( $( ".collapsible" ) );
                M.toast({html: 'Save row success!'})
            },
            error:function(data) {
                alert("error");
            }

        });

    }
})
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

    swal({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.value) {

        var link = "/trainroute/"+elementid+"/delete";
        var trainData={};
        $.ajax({
            type: "POST",
            url: "http://localhost:8081"+link,
            success:function(data) {
                M.toast({html: 'Delete row success!'})
                var el = "#headrow"+elementid;
                $(el).remove();
                el = "#headdetails"+elementid;
                $(el).remove();
            }
        });

    }
})

    return false;
}

//not used
function saveItemTR(elementid) {
    event.stopPropagation();

    swal({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.value) {

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
            url: "http://localhost:8081"+link,
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
})

    return false;
}

function saveItemNew() {
    // event.stopPropagation();

    swal({
        title: 'Are you sure?',
        text: "",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, save it!'
    }).then((result) => {
        if (result.value) {

        var routeData={};
        routeData["traincode"] = $("#traincode_new").val();
        routeData["arrst"] = $("#arrst_new").val();
        routeData["depst"] = $("#depst_new").val();
        datareq= JSON.stringify( routeData );
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8081/trainroute/add",
            data: datareq,
            success:function(data) {
                $('#modal').html("");
                //$( ".hoverable:last" ).append( data);
                $( data ).appendTo( $( ".collapsible" ) );
                M.toast({html: 'Save route success!'})
                location.reload();
            },
            error:function(data) {
                alert("error");
            }

        });
    }
})

}