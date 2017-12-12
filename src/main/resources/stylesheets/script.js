function sortTable(tableId, n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById(tableId);
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
            one from current row and one from the next: */
            //remove cm and kg from string to compare numbers
            var x = rows[i].getElementsByTagName("TD")[n].innerHTML.replace("kg","").replace("cm","").replace(",",".");
            var y = rows[i + 1].getElementsByTagName("TD")[n].innerHTML.replace("kg","").replace("cm","").replace(",",".");
            /* Check if the two rows should switch place,
            based on the direction, asc or desc: */
            if (dir == "asc") {
                //either compare numbers or string
                if (isNaN(parseFloat(x.replace(/[^\d.-]/g, '')))){
                    if (x.toLowerCase() > y.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch= true;
                        break;
                    }
                } else {
                    if(x - y > 0){
                        shouldSwitch= true;
                        break;
                    }
                }
            } else if (dir == "desc") {
                //either compare numbers or string
                if (isNaN(parseFloat(x.replace(/[^\d.-]/g, '')))){
                    if (x.toLowerCase() < y.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch= true;
                        break;
                    }
                } else {
                    if(x - y < 0){
                        shouldSwitch= true;
                        break;
                    }
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
            and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount ++;
        } else {
            /* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function toggle_visibility(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block') {
        //die verzögerung ist nötig da man sonst keine links öffnen kann
        var meinTimeout = setTimeout(function() {
            e.style.display = 'none';
        }, 200);
    } else {
        e.style.display = 'block';
        document.getElementById(id+'Content').focus();
    }
}