# ⚡ Bolt API - Documentación de Endpoints

Bienvenido a la documentación oficial de la API de **Bolt**.
Este documento detalla las rutas disponibles, los métodos necesarios y los formatos de respuesta.

**Base URL:** `https://bolt-backend-c99u.onrender.com`

---

## 🔐 1. Autenticación (`Auth`)

Controlador: `AuthController` | Base Path: `/auth`

| Acción        | Método | Endpoint         | Body (JSON)                              | Respuesta Exitosa                       |
| :------------ | :----: | :--------------- | :--------------------------------------- | :-------------------------------------- |
| **Registrar** | `POST` | `/auth/register` | `fullName`, `email`, `phone`, `password` | `200 OK`: "User registered succesfully" |
| **Login**     | `POST` | `/auth/login`    | `email`, `password`                      | `200 OK`: `{"token": "..."}`            |

---

## 👤 2. Usuarios (`User`)

Controlador: `UserController` | Base Path: `/users`  
_Requiere Header: `Authorization: Bearer <token>`_

| Acción              |  Método  | Endpoint             | Parámetros / Body             | Respuesta                        |
| :------------------ | :------: | :------------------- | :---------------------------- | :------------------------------- |
| **Listar Usuarios** |  `GET`   | `/users/allUsers`    | Ninguno                       | `200 OK`: Lista `UserProjectDTO` |
| **Usuario Actual**  |  `GET`   | `/users/currentUser` | Ninguno                       | `200 OK`: Datos de mi perfil     |
| **Actualizar**      |  `PUT`   | `/users/update/{id}` | `UserDTO` (Body) + `id` (URL) | `200 OK`: User actualizado       |
| **Eliminar**        | `DELETE` | `/users/delete/{id}` | `id` (URL)                    | `204 No Content`                 |

---

## 🚀 3. Proyectos (`Project`)

Controlador: `ProjectController` | Base Path: `/projects`
_Requiere Header: `Authorization: Bearer <token>`_

### Gestión de Proyectos

| Acción             |  Método  | Endpoint                   | Parámetros / Body  | Respuesta                              |
| :----------------- | :------: | :------------------------- | :----------------- | :------------------------------------- |
| **Listar Todos**   |  `GET`   | `/projects/allProjects`    | Ninguno            | `200 OK`: Lista `ProjectDTO`           |
| **Mis Proyectos**  |  `GET`   | `/projects/userProjects`   | Token Auth         | `200 OK`: Proyectos del usuario actual |
| **Crear Proyecto** |  `POST`  | `/projects/createdProject` | `ProjectCreateDTO` | `201 Created`: Datos del proyecto      |
| **Actualizar**     |  `PUT`   | `/projects/update/{id}`    | `ProjectEditDTO`   | `200 OK`: Proyecto editado             |
| **Eliminar**       | `DELETE` | `/projects/delete/{id}`    | `id` (URL)         | `204 No Content`                       |

### Miembros del Proyecto

| Acción             |  Método  | Endpoint                               | Descripción                     |
| :----------------- | :------: | :------------------------------------- | :------------------------------ |
| **Ver Miembros**   |  `GET`   | `/projects/{projectId}/users`          | Usuarios asignados al proyecto  |
| **Añadir Miembro** |  `POST`  | `/projects/{projectId}/users/{userId}` | Asigna un usuario al proyecto   |
| **Quitar Miembro** | `DELETE` | `/projects/{projectId}/users/{userId}` | Elimina un usuario del proyecto |

---

## ✅ 4. Tareas (`Task`)

Controlador: `TaskController` | Base Path: `/tasks`
_Requiere Header: `Authorization: Bearer <token>`_

| Acción                  |  Método  | Endpoint                      | Parámetros / Body    | Respuesta                      |
| :---------------------- | :------: | :---------------------------- | :------------------- | :----------------------------- |
| **Crear Tarea**         |  `POST`  | `/tasks/createdTask`          | `TaskDTO`            | `201 Created`                  |
| **Listar Todas**        |  `GET`   | `/tasks/allTasks`             | Ninguno              | `200 OK`: Lista `TaskDTO`      |
| **Tareas por Proyecto** |  `GET`   | `/tasks/{projectId}/allTasks` | `projectId` (URL)    | `200 OK`: Lista `TaskAdminDTO` |
| **Actualizar Completa** |  `PUT`   | `/tasks/update/{id}`          | `TaskAdminDTO`       | `200 OK`: Tarea editada        |
| **Actualizar Estado**   |  `PUT`   | `/tasks/updateState/{id}`     | `{"state": "VALUE"}` | `200 OK`: Estado actualizado   |
| **Eliminar Tarea**      | `DELETE` | `/tasks/delete/{id}`          | `id` (URL)           | `204 No Content`               |

---

## 🛠️ Guía de Pruebas Rápidas

1. **Headers:** Todas las peticiones (excepto /auth) deben llevar:
   - `Authorization: Bearer <token>`
   - `Content-Type: application/json`
2. **Códigos de Estado:** - `200/201`: Éxito.
   - `204`: Eliminación exitosa (sin contenido).
   - `401`: Token no válido o credenciales erróneas.
   - `404`: El recurso (ID) no existe.
3. **Usuarios para el uso de la app:** - `Administrador/Usuario`
   - `Administrador`: Correo -> `admin@admin.com` | Contraseña -> `12345678`.
   - `Usuarios`: Correo -> `jesus@gmail.com` / `antonio@gmail.com` | Contraseña -> para los dos es la misma `12345678`.
---
