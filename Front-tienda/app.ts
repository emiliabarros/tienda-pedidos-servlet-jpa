export interface PedidoDTO {
    id: number;
    clienteNombre: string;
    productos: ProductoDTO[];
    fechaPedido: string;
    total: number;
}

export interface ProductoDTO {
    nombre: string;
    precio: number;
}

function cargarDatos() {
    const body = document.getElementById('tabla')?.querySelector('tbody');
    body!.innerHTML = ''; // Limpiar datos previos
    const detalle = document.getElementById('detalle');
    detalle!.innerHTML = ''; // Limpiar detalles previos

    fetch("http://localhost:8082/tienda/pedidos")
        .then(respuesta => respuesta.json())
        .then((pedidos: PedidoDTO[]) => {
            pedidos.forEach(pedido => {
                const row = document.createElement('tr'); // Crear una nueva fila
                const col1 = document.createElement('td'); // Crear una nueva columna
                col1.textContent = pedido.fechaPedido; // Asignar el valor a la columna
                const col2 = document.createElement('td');
                col2.textContent = pedido.clienteNombre;
                const col3 = document.createElement('td');
                col3.textContent = pedido.total.toString();
                const col4 = document.createElement('td');
                const icon = document.createElement('i');
                icon.classList.add('info-icon');
                icon.textContent = 'i';
                icon.addEventListener('click', () => {
                    verDetalle(pedido.id);
                });
                col4.appendChild(icon);
                row.appendChild(col1);
                row.appendChild(col2);
                row.appendChild(col3);
                row.appendChild(col4);
                body?.appendChild(row);
            });
        }).catch(error => {
            console.error('Error al cargar los datos:', error);
        });
}

function verDetalle(idPedido: number) {
    fetch(`http://localhost:8082/tienda/pedidos?id=${idPedido}`)
        .then(respuesta => respuesta.json())
        .then((pedido: PedidoDTO) => {
            const detalle = document.getElementById('detalle');
            detalle!.innerHTML = ''; // Limpiar detalles previos
            const titulo = document.createElement('h3');
            titulo.textContent = `Detalle del Pedido #${pedido.id}`;
            detalle?.appendChild(titulo);
            const lista = document.createElement('ul');
            pedido.productos.forEach(producto => {
                const item = document.createElement('li');
                item.textContent = `${producto.nombre} - $${producto.precio}`;
                lista.appendChild(item);
            });
            detalle?.appendChild(lista);
        })
        .catch(error => {
            console.error('Error al cargar los detalles del pedido:', error);
        });
}
