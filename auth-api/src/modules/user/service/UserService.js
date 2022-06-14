import userRepository from "../repository/userRepository";
import * as httpStatus from "../../../config/constants/httpStatus.js";

class UserService {

    async findByEmail(req){
        try {
            const {email} = req.params;
            this.validarDadosRequisicao(email);
            let user = userRepository.findByEmail(email);
            if(!user) {
                return {
                    status: httpStatus.BAD_REQUEST,
                    message: "Usuário não encontrado."
                }
            }
            return {
                status: httpStatus.SUCCESS,
                user: {
                    id: user.id,
                    nome: user.nome,
                    email: user.email
                }
            }
        } catch (error) {
            return {
                status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: error.status
            }
        }
    }

    validarDadosRequisicao(email){
        if(!email){
            throw new Error("Email informado inválido");
        }
    }

}

export default new UserService();