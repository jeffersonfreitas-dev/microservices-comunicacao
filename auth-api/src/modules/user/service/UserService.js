import userRepository from "../repository/userRepository.js";
import * as httpStatus from "../../../config/constants/httpStatus.js";
import UserException from "../exception/UserException.js";
import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";
import * as secrets from "../../../config/constants/secrets.js";

class UserService {

    async findByEmail(req){
        try {
            const {email} = req.params;
            const {authUser} = req.params;
            this.validarDadosRequisicao(email);
            let user = await userRepository.findByEmail(email);
            this.validarUsuarioNaoEncontrado(user);
            this.validarUsuarioAutenticado(user, authUser);
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
                message: error.message
            }
        }
    }

    validarDadosRequisicao(email){
        if(!email){
            throw new UserException(httpStatus.BAD_REQUEST, "Email informado inválido");
        }
    }

    validarUsuarioNaoEncontrado(user){
        if(!user){
           throw new UserException(httpStatus.BAD_REQUEST, "Usuário não encontrado"); 
        }
    }

    validarUsuarioAutenticado(user, authUser){
        if(!authUser || user.id != authUser.id){
            throw new UserException(httpStatus.FORBIDDEN, "Você não pode ver os dados deste usuário");
        }
    }


    async getAccessToken(req) {
        try {
            const {email, password} = req.body;
            this.validarEmailEPassword(email, password); 
            let user = await userRepository.findByEmail(email);
            this.validarUsuarioNaoEncontrado(user);
            await this.validarPassword(password, user.password);
            const accessToken = jwt.sign({id: user.id, name: user.name, email: user.email}, secrets.API_SECRET ,{expiresIn: "1d"});
            return {
                status: httpStatus.SUCCESS,
                accessToken
            }
        } catch (error) {
            return {
                status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: error.message
            }
        }

    }

    validarEmailEPassword(email, password) {
        if(!email || !password) {
            throw new UserException(httpStatus.UNAUTHORIZED, "Email e senha deve ser informado");
        }
    }

    async validarPassword(password, hashPassword){
        if(!(await bcrypt.compare(password, hashPassword))){
            throw new UserException(httpStatus.UNAUTHORIZED, "Password não deu match")
        }
    }

}

export default new UserService();