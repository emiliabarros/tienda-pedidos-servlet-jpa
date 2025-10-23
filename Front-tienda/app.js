"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function cargarDatos() {
    var _a;
    var body = (_a = document.getElementById('tabla')) === null || _a === void 0 ? void 0 : _a.querySelector('tbody');
    body.innerHTML = ''; // Limpiar datos previos
    var detalle = document.getElementById('detalle');
    detalle.innerHTML = ''; // Limpiar detalles previos
    fetch("http://localhost:8082/tienda/pedidos")
        .then(function (respuesta) { return respuesta.json(); })
        .then(function (pedidos) {
        pedidos.forEach(function (pedido) {
            var row = document.createElement('tr'); // Crear una nueva fila
            var col1 = document.createElement('td'); // Crear una nueva columna
            col1.textContent = pedido.fechaPedido; // Asignar el valor a la columna
            var col2 = document.createElement('td');
            col2.textContent = pedido.clienteNombre;
            var col3 = document.createElement('td');
            col3.textContent = pedido.total.toString();
            var col4 = document.createElement('td');
            var icon = document.createElement('i');
            icon.classList.add('info-icon');
            icon.textContent = 'i';
            icon.addEventListener('click', function () {
                verDetalle(pedido.id);
            });
            col4.appendChild(icon);
            row.appendChild(col1);
            row.appendChild(col2);
            row.appendChild(col3);
            row.appendChild(col4);
            body === null || body === void 0 ? void 0 : body.appendChild(row);
        });
    }).catch(function (error) {
        console.error('Error al cargar los datos:', error);
    });
}
function verDetalle(idPedido) {
    fetch("http://localhost:8082/tienda/pedidos?id=".concat(idPedido))
        .then(function (respuesta) { return respuesta.json(); })
        .then(function (pedido) {
        var detalle = document.getElementById('detalle');
        detalle.innerHTML = ''; // Limpiar detalles previos
        var titulo = document.createElement('h3');
        titulo.textContent = "Detalle del Pedido #".concat(pedido.id);
        detalle === null || detalle === void 0 ? void 0 : detalle.appendChild(titulo);
        var lista = document.createElement('ul');
        pedido.productos.forEach(function (producto) {
            var item = document.createElement('li');
            item.textContent = "".concat(producto.nombre, " - $").concat(producto.precio);
            lista.appendChild(item);
        });
        detalle === null || detalle === void 0 ? void 0 : detalle.appendChild(lista);
    })
        .catch(function (error) {
        console.error('Error al cargar los detalles del pedido:', error);
    });
}
