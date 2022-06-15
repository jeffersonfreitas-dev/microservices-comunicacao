import express from "express";

import * as db from "./src/config/db/InitialData.js";
import userRouters from "./src/modules/user/routes/UserRoutes.js";

db.createInitialData();

const app = express();
const env = process.env;
const PORT = env.port || 8080;

app.use(express.json());

app.use(userRouters);

app.get('/api/status', (req, res) => {
    return res.status(200).json({
        service: 'Auth-API',
        status: 'up',
        httpStatus: 200
    })
});

app.listen(PORT, () => {
    console.info(`Servidor iniciado com sucesso na porta ${PORT}`)
})