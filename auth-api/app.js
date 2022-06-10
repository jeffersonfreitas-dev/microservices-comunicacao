import express from "express";

const app = express();
const env = process.env;
const PORT = env.port || 8080;

app.listen(PORT, () => {
    console.info(`Servidor iniciado com sucesso na porta ${PORT}`)
})