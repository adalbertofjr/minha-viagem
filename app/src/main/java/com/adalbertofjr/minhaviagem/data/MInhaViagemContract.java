package com.adalbertofjr.minhaviagem.data;

/**
 * Created by AdalbertoF on 25/01/2016.
 */
public class MinhaViagemContract {

    public static final class ViagemEntry{

        public static final String TABLE_NAME = "VIAGEM";
        public static final String ID = "_id";
        public static final String DESTINO = "destino";
        public static final String TIPO_VIAGEM = "tipo_viagem";
        public static final String DATA_CHEGADA = "data_chegada";
        public static final String DATA_SAIDA = "data_saida";
        public static final String ORCAMENTO = "orcamento";
        public static final String QTD_PESSOAS = "quantidade_pessoas";

        public static final int VIAGEM_LAZER = 1;
        public static final int VIAGEM_NEGOCIOS = 2;
    }

    public static final class GastoEntry{

        public static final String TABLE_NAME = "GASTO";
        public static final String ID = "_id";
        public static final String CATEGORIA = "categoria";
        public static final String DATA = "data";
        public static final String VALOR = "valor";
        public static final String DESCRICAO = "descricao";
        public static final String LOCAL = "local";
        public static final String VIAGEM_ID = "viagem_id";
    }
}
