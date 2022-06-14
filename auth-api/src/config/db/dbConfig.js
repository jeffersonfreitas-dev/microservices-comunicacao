import  Sequelize  from "sequelize";

const sequelize = new Sequelize("auth-db","postgres", "1q2w3e4r5t", {
    host: "localhost",
    dialect: "postgres",
    quoteIdentifiers: false,
    define: {
        syncOnAssociation: true,
        timestamps: false,
        underscored: true,
        underscoredAll: true,
        freezeTableName: true
    }
});

sequelize
    .authenticate()
    .then(() => {
        console.info("Conexão foi estabelecida");
    })
    .catch((err) => {
        console.error("Não foi possivel estabelecer conexão")
        console.error(err.message);
    })

export default sequelize;