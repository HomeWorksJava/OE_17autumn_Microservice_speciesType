/**
 * Created by pati on 2017-11-13.
 */



function init() {
    refresh();
}

function refresh() {
    var table = document.getElementById('table');
    table.innerHTML = '';
    getSpeciesTypes();
}

function parse() {
    console.log(this.response);
    var table = document.getElementById('table');
    addTableRow(table, {0: 'Id', 1: 'Name', 2: 'Description'}, 'th');
    for (var x in this.response) {
        addTableRow(table, this.response[x], 'td');
    }
}

function addTableRow(table, items, celltype) {
    var tr = document.createElement("tr");
    table.appendChild(tr);
    for (var item in items) {
        var td = document.createElement(celltype);
        td.textContent = items[item];
        tr.appendChild(td);
    }
}

function getSpeciesTypes() {
    var oReq = new XMLHttpRequest();
    oReq.responseType = 'json';
    oReq.addEventListener('load', parse);
    oReq.open('GET', 'http://localhost:8080/speciestypes');
    oReq.send();
}

function handleDelete() {
    console.log(this.response);
    refresh();
}

function deleteSpeciesType(){
    var oReq = new XMLHttpRequest();
    oReq.addEventListener('load', handleDelete);
    var id = document.getElementById('delete-id').value;
    var link = 'http://localhost:8080/deletespeciestype/' + id;
    console.log('Deleting ' + id + ' with link: ' + link);
    oReq.open('DELETE', link);
    oReq.send();
}

function handlePost() {
    console.log(this.response);
    refresh();

}
function post() {
    var name = document.getElementById('new-name').value;
    var description = document.getElementById('new-description').value;
    var st = speciesType(0, name, description);

    var link = 'http://localhost:8080/postspeciestype';
    var postReq = new XMLHttpRequest();
    postReq.open("POST", link);
    postReq.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    postReq.addEventListener('load', handlePost);
    postReq.send(st.toParamString());

    console.log(link)
}

function speciesType(id, name, description) {
    return {
        id: id,
        name: name,
        description: description,
        toParamString: function () {
            return 'name=' + name + '&description=' + description;
        }
    }
}
