<h1 align="center"> Project Portfolio Backend Frontend </h1>

## Descrição
Projeto web java usando spring-boot, servlet, JSP e persistência com banco de dados.

## Backend
* <h1><b>I. Banco de dados: PostgreSQL 15 </b></h1> 
* BASE DE DADOS:

  CREATE DATABASE "project-portfolio" WITH OWNER = postgres ENCODING = 'UTF8'
  LC_COLLATE = 'Portuguese_Brazil.1252'
  LC_CTYPE = 'Portuguese_Brazil.1252'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1
  IS_TEMPLATE = False;

* ENTIDADES/TABELAS:

  CREATE TABLE IF NOT EXISTS public.usuarios
  (
  id bigint NOT NULL DEFAULT nextval('usuarios_id_seq'::regclass),
  login character varying(250) COLLATE pg_catalog."default" NOT NULL,
  senha character varying(250) COLLATE pg_catalog."default" NOT NULL,
  nome character varying(250) COLLATE pg_catalog."default" NOT NULL,
  status boolean NOT NULL DEFAULT true,
  CONSTRAINT pk_usuarios PRIMARY KEY (id)
  )
  TABLESPACE pg_default;

=========================================================================================

	CREATE TABLE IF NOT EXISTS public.pessoas
	(
		id bigint NOT NULL DEFAULT nextval('pessoas_id_seq'::regclass),
		nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
		datanascimento date,
		cpf character varying(14) COLLATE pg_catalog."default",
		funcionario boolean,
		CONSTRAINT pk_pessoas PRIMARY KEY (id)
	)
	TABLESPACE pg_default;

=========================================================================================

	CREATE TABLE IF NOT EXISTS public.projetos
	(
		id bigint NOT NULL DEFAULT nextval('projetos_id_seq'::regclass),
		nome character varying(200) COLLATE pg_catalog."default" NOT NULL,
		data_inicio date,
		data_previsao_fim date,
		data_fim date,
		descricao character varying(5000) COLLATE pg_catalog."default",
		status character varying(45) COLLATE pg_catalog."default",
		orcamento double precision,
		risco character varying(45) COLLATE pg_catalog."default",
		CONSTRAINT pk_projetos PRIMARY KEY (id)
	)
	TABLESPACE pg_default;

* <h1><b>II. Microservico: Spring-Boot 3.0.6 </b></h1>
* IntelliJ 2022.2.2;
* Java - versão 16;
* Maven;
* Lombok;
* JUnit - Mockito;
* Hibernate;
* Postman - v10.14:
*   Endpoints:
*     GET
      http://localhost:8081/api/v1/project/portfolio/pessoas
      http://localhost:8081/api/v1/project/portfolio/projetos
      http://localhost:8081/api/v1/project/portfolio/projetos/1/pessoas
      http://localhost:8081/api/v1/project/portfolio/pessoas/funcionario
      http://localhost:8081/api/v1/project/portfolio/pessoas/7
      http://localhost:8081/api/v1/project/portfolio/pessoas/1
      POST
      http://localhost:8081/api/v1/project/portfolio/projetos/1/pessoas
        {
          "id": 2
        }
      http://localhost:8081/api/v1/project/portfolio/pessoas
        {
          "nome": "Master",
          "dataNascimento": "2013-01-05",
          "cpf": "111.333.555-77",
          "funcionario": true
        }
      http://localhost:8081/api/v1/project/portfolio/projetos
        {
          "nome": "Projeto cálculo PIS/CONFINS 2024",
          "dataInicio": "2023-05-01T09:05:30.523",
          "dataPrevisaoFim": "2024-10-11T09:05:30.523",
          "dataFim": "2024-12-20T09:05:30.523",
          "descricao": "Projeto para calcular os diferentes ICMS nos estados do Brasil",
          "status": "EM_ANALISE",
          "orcamento": 500000,
          "risco": "ALTO",
          "idGerente": 2
        }
      PUT
      http://localhost:8081/api/v1/project/portfolio/pessoas/6
        {
          "nome": "King",
          "datanascimento": "1948-04-07",
          "cpf": "222.333.888-99",
          "funcionario": false
        }

## Frontend
>Tecnologias, ferramentas e frameworks utilizados:
* <h1><b>I. Projeto Web: Servlet, JSP e CSS</b></h1>
* Eclipse - 2022-12 (4.26.0);
* Java - versão 19;
* Tomcat - 10.1 :
*    Porta: 8080
*    Endpoint: http://localhost:8080/project-portfolio-backend-frontend/
*       . Para acessar deve se incluir um usuário na base de dados.