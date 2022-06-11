import express from "express";

const app = express();
const env = process.env;
const PORT = env.port || 8080;

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