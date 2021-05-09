$(document).ready(function () {
});

function loadUsers() {
    //console.log("loadUsers function... param is: " + json);
    $.ajax({
        type: 'POST',
        data: {
            act: "loadUsers"
        },
        url: '/mainServlet',
        success: function (json) {
            jsonToTable(json);
        }
    });
}


function jsonToTable(json) {
    //console.log("jsonToTable function... param is: " + json);
    var parsejson = JSON.parse(json);
    var columns = [];
    var tablethead = "<thead><tr>";
    var colNo = 1;
    for (x in parsejson[0]) {
        columns.push(x);
        if (colNo == 1)
            tablethead += "<th style='display: none'>" + x + "</th>";
        else
            tablethead += "<th>" + x + "</th>";
        colNo++;
    }
    tablethead += "</tr></thead>";
    document.getElementById("tableID").innerHTML = tablethead;
    var table_rows = '<tbody>';
    for (var i = 0; i < parsejson.length; i++) {
        var x = parsejson[i];
        var json2 = x;
        var row = "<tr>";
        var cellNo = 1;
        for (d in x) {
            var sty = x[d];
            if (sty != null) {
                var st = sty.toString();
                var reps = '<\\';
                if (cellNo == 1) {
                    row += "<td style='display: none'>" + st.split('<').join('&lt;') + "</td>";
                } else {
                    row += "<td name=" + cellNo + ">" + st.split('<').join('&lt;') + "</td>";
                }
                cellNo++;
            } else {
                row += "<td>null</td>";
            }
        }
        row += "</tr>"
        table_rows += row;
    }
    table_rows += '</tbody>';
    document.getElementById("tableID").innerHTML += table_rows;
    var column = "table #" + $(this).attr("id");
    $(column).hide();
}


/*
function deleteUser(userId) {
 //console.log("deleteUser... param is: " + userId);
	//refreshPage();
	 	 	$.ajax({
			url : '/MainServlet',
			data : {
				id : userId,
				act: "DeleteUser",
			},
			success : function(data) {
				console.log(data);
				$("#table1").html("");
				$("#table1").html(data);
				alert("success delete user");
			}
		});
}
*/


function report(Sign){
   // console.log("report function... param is: "Sign);
    document.getElementById('act').value=Sign;
}

