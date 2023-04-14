--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2023-04-14 10:24:05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3446 (class 1262 OID 1188331)
-- Name: pharmaproto; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE pharmaproto WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'French_France.1252';


\connect pharmaproto

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 3447 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 228 (class 1259 OID 1204833)
-- Name: approvisionnement; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.approvisionnement (
    approvisionnement_id integer NOT NULL,
    medicament_id integer,
    fournisseur_id integer,
    quantite_commande integer,
    quantite_recu integer,
    status character varying(255),
    date_creation date DEFAULT CURRENT_DATE,
    date_recu date,
    prix integer,
    prix_fournisseur integer
);


--
-- TOC entry 227 (class 1259 OID 1204832)
-- Name: approvisionnement_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.approvisionnement_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3448 (class 0 OID 0)
-- Dependencies: 227
-- Name: approvisionnement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.approvisionnement_id_seq OWNED BY public.approvisionnement.approvisionnement_id;


--
-- TOC entry 226 (class 1259 OID 1204824)
-- Name: client; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.client (
    client_id integer NOT NULL,
    client_nom character varying(255),
    client_prenom character varying(255),
    age integer,
    client_adresse character varying(255),
    numero integer,
    nom_medecin character varying(255),
    date_creation date DEFAULT CURRENT_DATE,
    active boolean,
    date_ajout date
);


--
-- TOC entry 225 (class 1259 OID 1204823)
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3449 (class 0 OID 0)
-- Dependencies: 225
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.client_id;


--
-- TOC entry 232 (class 1259 OID 1204862)
-- Name: commande; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.commande (
    commande_id integer NOT NULL,
    facture_id integer,
    medicament_id integer,
    quantite integer,
    prix_unitaire integer
);


--
-- TOC entry 231 (class 1259 OID 1204861)
-- Name: commande_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.commande_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3450 (class 0 OID 0)
-- Dependencies: 231
-- Name: commande_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.commande_id_seq OWNED BY public.commande.commande_id;


--
-- TOC entry 230 (class 1259 OID 1204850)
-- Name: facture; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.facture (
    facture_id integer NOT NULL,
    client_id integer,
    date_creation date DEFAULT CURRENT_DATE,
    heure date,
    prix_total integer
);


--
-- TOC entry 229 (class 1259 OID 1204849)
-- Name: facture_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.facture_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3451 (class 0 OID 0)
-- Dependencies: 229
-- Name: facture_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.facture_id_seq OWNED BY public.facture.facture_id;


--
-- TOC entry 218 (class 1259 OID 1204760)
-- Name: famille; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.famille (
    famille_id integer NOT NULL,
    famille_nom character varying,
    active boolean DEFAULT true,
    date_ajout date DEFAULT CURRENT_DATE
);


--
-- TOC entry 217 (class 1259 OID 1204759)
-- Name: famille_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.famille_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 217
-- Name: famille_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.famille_id_seq OWNED BY public.famille.famille_id;


--
-- TOC entry 220 (class 1259 OID 1204771)
-- Name: forme; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.forme (
    forme_id integer NOT NULL,
    forme_nom character varying NOT NULL,
    active boolean DEFAULT true,
    date_ajout date DEFAULT CURRENT_DATE
);


--
-- TOC entry 219 (class 1259 OID 1204770)
-- Name: forme_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.forme_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 219
-- Name: forme_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.forme_id_seq OWNED BY public.forme.forme_id;


--
-- TOC entry 216 (class 1259 OID 1204750)
-- Name: fournisseur; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.fournisseur (
    fournisseur_id integer NOT NULL,
    fournisseur_adresse character varying NOT NULL,
    fournisseur_email character varying NOT NULL,
    fournisseur_telephone integer NOT NULL,
    fournisseur_nom character varying NOT NULL,
    active boolean DEFAULT true,
    date_ajout date DEFAULT CURRENT_DATE
);


--
-- TOC entry 215 (class 1259 OID 1204749)
-- Name: fournisseur_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.fournisseur_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 215
-- Name: fournisseur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.fournisseur_id_seq OWNED BY public.fournisseur.fournisseur_id;


--
-- TOC entry 224 (class 1259 OID 1204808)
-- Name: fournisseur_prix; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.fournisseur_prix (
    fournisseur_prix_id integer NOT NULL,
    fournisseur_id integer,
    medicament_id integer,
    fournisseur_prix_prix double precision,
    prix_fournisseur double precision,
    date_ajout date DEFAULT CURRENT_DATE
);


--
-- TOC entry 223 (class 1259 OID 1204807)
-- Name: fournisseur_prix_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.fournisseur_prix_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 223
-- Name: fournisseur_prix_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.fournisseur_prix_id_seq OWNED BY public.fournisseur_prix.fournisseur_prix_id;


--
-- TOC entry 222 (class 1259 OID 1204782)
-- Name: medicament; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.medicament (
    medicament_id integer NOT NULL,
    medicament_nom character varying NOT NULL,
    dose character varying NOT NULL,
    medicament_quantite integer,
    id_fournisseur integer,
    active boolean DEFAULT true,
    date_ajout date DEFAULT CURRENT_DATE,
    date_fabrication date,
    date_expiration date,
    description text,
    id_forme integer,
    id_famille integer
);


--
-- TOC entry 221 (class 1259 OID 1204781)
-- Name: medicament_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.medicament_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 221
-- Name: medicament_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.medicament_id_seq OWNED BY public.medicament.medicament_id;


--
-- TOC entry 212 (class 1259 OID 1204725)
-- Name: role; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.role (
    role_id integer NOT NULL,
    role_nom character varying(255),
    description character varying(255),
    date_ajout date DEFAULT CURRENT_DATE
);


--
-- TOC entry 211 (class 1259 OID 1204724)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3457 (class 0 OID 0)
-- Dependencies: 211
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.role_id;


--
-- TOC entry 210 (class 1259 OID 1204716)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_nom character varying(255),
    user_prenom character varying(255),
    user_email character varying(255),
    user_adresse character varying(255),
    user_username character varying(255),
    user_telephone integer,
    user_password character varying(255),
    date_creation date DEFAULT CURRENT_DATE
);


--
-- TOC entry 209 (class 1259 OID 1204715)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3458 (class 0 OID 0)
-- Dependencies: 209
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".user_id;


--
-- TOC entry 214 (class 1259 OID 1204734)
-- Name: user_role; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_role (
    user_role_id integer NOT NULL,
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    date_creation date DEFAULT CURRENT_DATE
);


--
-- TOC entry 213 (class 1259 OID 1204733)
-- Name: user_role_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.user_role ALTER COLUMN user_role_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3240 (class 2604 OID 1204836)
-- Name: approvisionnement approvisionnement_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.approvisionnement ALTER COLUMN approvisionnement_id SET DEFAULT nextval('public.approvisionnement_id_seq'::regclass);


--
-- TOC entry 3238 (class 2604 OID 1204827)
-- Name: client client_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client ALTER COLUMN client_id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- TOC entry 3244 (class 2604 OID 1204865)
-- Name: commande commande_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.commande ALTER COLUMN commande_id SET DEFAULT nextval('public.commande_id_seq'::regclass);


--
-- TOC entry 3242 (class 2604 OID 1204853)
-- Name: facture facture_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facture ALTER COLUMN facture_id SET DEFAULT nextval('public.facture_id_seq'::regclass);


--
-- TOC entry 3227 (class 2604 OID 1204763)
-- Name: famille famille_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.famille ALTER COLUMN famille_id SET DEFAULT nextval('public.famille_id_seq'::regclass);


--
-- TOC entry 3230 (class 2604 OID 1204774)
-- Name: forme forme_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.forme ALTER COLUMN forme_id SET DEFAULT nextval('public.forme_id_seq'::regclass);


--
-- TOC entry 3224 (class 2604 OID 1204753)
-- Name: fournisseur fournisseur_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fournisseur ALTER COLUMN fournisseur_id SET DEFAULT nextval('public.fournisseur_id_seq'::regclass);


--
-- TOC entry 3236 (class 2604 OID 1204811)
-- Name: fournisseur_prix fournisseur_prix_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fournisseur_prix ALTER COLUMN fournisseur_prix_id SET DEFAULT nextval('public.fournisseur_prix_id_seq'::regclass);


--
-- TOC entry 3233 (class 2604 OID 1204785)
-- Name: medicament medicament_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.medicament ALTER COLUMN medicament_id SET DEFAULT nextval('public.medicament_id_seq'::regclass);


--
-- TOC entry 3221 (class 2604 OID 1204728)
-- Name: role role_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role ALTER COLUMN role_id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- TOC entry 3219 (class 2604 OID 1204719)
-- Name: user user_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- TOC entry 3436 (class 0 OID 1204833)
-- Dependencies: 228
-- Data for Name: approvisionnement; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3434 (class 0 OID 1204824)
-- Dependencies: 226
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3440 (class 0 OID 1204862)
-- Dependencies: 232
-- Data for Name: commande; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3438 (class 0 OID 1204850)
-- Dependencies: 230
-- Data for Name: facture; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3426 (class 0 OID 1204760)
-- Dependencies: 218
-- Data for Name: famille; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.famille VALUES (9, 'famille 9', true, '2023-02-15');
INSERT INTO public.famille VALUES (20, 'famille 20', true, '2023-02-15');
INSERT INTO public.famille VALUES (11, 'famille 11', true, '2023-02-15');
INSERT INTO public.famille VALUES (18, 'famille 18', false, '2023-02-15');
INSERT INTO public.famille VALUES (14, 'famille 14', true, '2023-02-15');
INSERT INTO public.famille VALUES (15, 'famille 15', true, '2023-02-15');
INSERT INTO public.famille VALUES (8, 'famille 8', true, '2023-02-15');
INSERT INTO public.famille VALUES (12, 'famille 12', true, '2023-02-15');
INSERT INTO public.famille VALUES (10, 'famille 10', true, '2023-02-15');
INSERT INTO public.famille VALUES (19, 'famille 19', true, '2023-02-15');
INSERT INTO public.famille VALUES (17, 'famille 17', true, '2023-02-15');
INSERT INTO public.famille VALUES (7, 'famille 7', true, '2023-02-15');
INSERT INTO public.famille VALUES (6, 'famille 6', true, '2023-02-15');
INSERT INTO public.famille VALUES (5, 'famille 5', true, '2023-02-15');
INSERT INTO public.famille VALUES (13, 'famille 13', false, '2023-02-15');
INSERT INTO public.famille VALUES (21, 'Cachet', true, '2023-03-12');
INSERT INTO public.famille VALUES (1, 'famille 1', false, '2023-02-15');
INSERT INTO public.famille VALUES (3, 'famille 3', false, '2023-02-15');
INSERT INTO public.famille VALUES (4, 'famille 4', false, '2023-02-15');
INSERT INTO public.famille VALUES (2, 'famille 2', false, '2023-02-15');


--
-- TOC entry 3428 (class 0 OID 1204771)
-- Dependencies: 220
-- Data for Name: forme; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.forme VALUES (1, 'Crème', true, '2023-03-12');
INSERT INTO public.forme VALUES (2, 'Pillule', true, '2023-03-12');
INSERT INTO public.forme VALUES (3, 'Ampoule', true, '2023-03-12');
INSERT INTO public.forme VALUES (4, 'Cachet', true, '2023-03-12');
INSERT INTO public.forme VALUES (5, 'chat', false, '2023-03-13');
INSERT INTO public.forme VALUES (6, 'asdfas', true, '2023-03-13');


--
-- TOC entry 3424 (class 0 OID 1204750)
-- Dependencies: 216
-- Data for Name: fournisseur; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.fournisseur VALUES (1, '233 random street', 'fournisseur1@email.com', 51234567, 'Fournisseur 1', true, NULL);
INSERT INTO public.fournisseur VALUES (3, '233 random street', 'fournisseur3@email.com', 51234567, 'Fournisseur 3', true, NULL);
INSERT INTO public.fournisseur VALUES (4, '233 random street', 'fournisseur4@email.com', 51234567, 'Fournisseur 4', true, NULL);
INSERT INTO public.fournisseur VALUES (5, '233 random street', 'fournisseur5@email.com', 51234567, 'Fournisseur 5', true, NULL);
INSERT INTO public.fournisseur VALUES (6, '233 random street', 'fournisseur6@email.com', 51234567, 'Fournisseur 6', true, NULL);
INSERT INTO public.fournisseur VALUES (7, '233 random street', 'fournisseur7@email.com', 51234567, 'Fournisseur 7', true, NULL);
INSERT INTO public.fournisseur VALUES (8, '233 random street', 'fournisseur8@email.com', 51234567, 'Fournisseur 8', true, NULL);
INSERT INTO public.fournisseur VALUES (9, '233 random street', 'fournisseur9@email.com', 51234567, 'Fournisseur 9', true, NULL);
INSERT INTO public.fournisseur VALUES (10, 'addresse random', 'FournisseurRandom@gmail.com', 12345678, 'Fournisseur Random', true, NULL);
INSERT INTO public.fournisseur VALUES (11, 'addresse random', 'FournisseurRandom@gmail.com', 12345678, 'Fournisseur Random', true, NULL);
INSERT INTO public.fournisseur VALUES (12, 'addresse random', 'FournisseurRandom@gmail.com', 12345678, ' ', true, NULL);
INSERT INTO public.fournisseur VALUES (13, 'addresse random', 'FournisseurRandom@gmail.com', 12345678, ' ', true, NULL);
INSERT INTO public.fournisseur VALUES (15, 'addresse random', 'FournisseurRandom@gmail.com', 12345678, 'Fournisseur Random', true, NULL);
INSERT INTO public.fournisseur VALUES (16, 'addresse random', 'FournisseurRandom@gmail.com', 12345678, 'Fournisseur intelliJ', true, NULL);
INSERT INTO public.fournisseur VALUES (2, '233 random street', 'fournisseur2@email.com', 51234567, 'Fournisseur 2', false, NULL);
INSERT INTO public.fournisseur VALUES (17, '32222222 ges ', 'email', 54315123, 'fournisseur', true, NULL);
INSERT INTO public.fournisseur VALUES (14, 'addresse random', 'FournisseurRandom@gmail.com', 12345678, '', false, NULL);


--
-- TOC entry 3432 (class 0 OID 1204808)
-- Dependencies: 224
-- Data for Name: fournisseur_prix; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.fournisseur_prix VALUES (5, 3, 3, 55.4, 55.4, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (2, 1, 15, 16.9, 16.9, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (18, 8, 8, 8.8, 8.8, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (14, 6, 6, 69.51, 69.51, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (8, 4, 18, 753.5, 753.5, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (3, 2, 2, 8, 8, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (6, 3, 17, 4.2, 4.2, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (7, 4, 4, 42.5, 42.5, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (9, 5, 21, 4312.6, 4312.6, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (20, 9, 9, 9.9, 9.9, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (19, 8, 13, 8.13, 8.13, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (12, 5, 10, 953.1, 953.1, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (21, 9, 14, 9.14, 9.14, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (17, 7, 7, 7.7, 7.7, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (16, 7, 12, 7.12, 7.12, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (4, 2, 16, 6.75, 6.75, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (15, 6, 20, 6.2, 6.2, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (1, 1, 1, 9.99, 9.99, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (13, 6, 11, 65.4, 65.4, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (11, 5, 19, 56.5, 56.5, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (10, 5, 5, 5.3, 5.3, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (22, 5, 3, 462, 6432, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (23, 4, 1, 645, 954.8, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (24, 6, 39, 333.329987, NULL, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (25, 8, 40, 32.299999, NULL, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (26, 7, 41, 32.299999, NULL, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (27, 7, 42, 32.299999, NULL, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (28, 8, 43, 35.299999, NULL, '2023-03-30');
INSERT INTO public.fournisseur_prix VALUES (29, 9, 44, 4312.339844, NULL, '2023-03-30');


--
-- TOC entry 3430 (class 0 OID 1204782)
-- Dependencies: 222
-- Data for Name: medicament; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.medicament VALUES (7, 'Panadol 6', '1000 mg', 564, 7, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 5, 17);
INSERT INTO public.medicament VALUES (15, 'Panadol 14', '1000 mg', 78, 1, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 2, 19);
INSERT INTO public.medicament VALUES (16, 'Panadol 15', '1000 mg', 654, 2, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 3, 4);
INSERT INTO public.medicament VALUES (6, 'Panadol 5', '1000 mg', 20, 6, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 3, 3);
INSERT INTO public.medicament VALUES (28, 'Yusuf', '10 jug', 789, 6, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 4, 2);
INSERT INTO public.medicament VALUES (33, 'nom', '10 mg', 57, 9, true, '2023-02-13', '2023-04-03', '2023-09-02', 'desdesdes', 3, 11);
INSERT INTO public.medicament VALUES (31, 'dr', 'hgd', 56, 5, true, '2023-02-13', '2023-11-03', '2023-07-02', 'tfccghf', 5, 7);
INSERT INTO public.medicament VALUES (8, 'Panadol 7', '1000 mg', 549, 8, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 1, 10);
INSERT INTO public.medicament VALUES (35, 'ccdsa', 'rre3', 1234, 9, true, '2023-02-13', '2023-03-03', '2023-01-02', 'fds', 1, 15);
INSERT INTO public.medicament VALUES (25, 'Pain', '10 mg', 645, 1, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 1, 4);
INSERT INTO public.medicament VALUES (14, 'Panadol 13', '1000 mg', 213, 9, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 5, 14);
INSERT INTO public.medicament VALUES (37, 'gggg', '4312 mg', 4, 4, true, '2023-03-12', '2023-01-02', '2023-01-03', 'ggggggggggggg', 1, 18);
INSERT INTO public.medicament VALUES (30, 'g', 'e', 32, 6, true, '2023-02-07', '2023-02-03', '2023-09-02', 'description testing', 4, 17);
INSERT INTO public.medicament VALUES (23, 'pana', '10 mg', 3, 1, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 3, 9);
INSERT INTO public.medicament VALUES (2, 'panadolJKHN', '1000 mg', 654, 2, false, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 6, 14);
INSERT INTO public.medicament VALUES (17, 'Panadol 16', '1000 mg', 312, 3, true, '2023-01-07', '2023-01-31', '2023-01-07', 'some description', 4, 13);
INSERT INTO public.medicament VALUES (11, 'Panadol 10', '1000 mg', 312, 6, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 4, 12);
INSERT INTO public.medicament VALUES (3, 'testNom', 'testDose', 777, 5, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 3, 8);
INSERT INTO public.medicament VALUES (10, 'Panadol 9', '1000 mg', 654, 5, true, '2023-01-07', '2023-01-07', '2023-01-07', 'rgsdfsgre', 3, 6);
INSERT INTO public.medicament VALUES (9, 'huh', '1000 mg', 78, 9, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 2, 1);
INSERT INTO public.medicament VALUES (36, 'gfjsjsjsjs', '10 mg', 43, 1, true, '2023-02-13', '2023-04-03', '2023-01-02', 'fgsdg', 2, 2);
INSERT INTO public.medicament VALUES (4, 'Panadol 3', '1000 mg', 21, 4, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 5, 5);
INSERT INTO public.medicament VALUES (13, 'Panadol 12', '1000 mg', 546, 8, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 4, 7);
INSERT INTO public.medicament VALUES (18, 'Panadol 17', '1000 mg', 21, 4, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 1, 9);
INSERT INTO public.medicament VALUES (5, 'Panadol 4', '1000 mg', 546, 5, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 2, 21);
INSERT INTO public.medicament VALUES (21, 'Panadol 20', '1000 mg', 564, 5, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 2, 11);
INSERT INTO public.medicament VALUES (29, 'another', '10 gig', 45432, 2, true, '2023-02-07', '2023-02-16', '2023-02-23', 'description', 1, 13);
INSERT INTO public.medicament VALUES (12, 'Panadol 11', '1000 mg', 21, 7, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 1, 8);
INSERT INTO public.medicament VALUES (34, 'gfsr', '23f', 232, 10, true, '2023-02-13', '2023-03-03', '2023-09-02', 'wegtfdsger', 2, 3);
INSERT INTO public.medicament VALUES (26, 'nom', '10mg', 56, 6, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 2, 12);
INSERT INTO public.medicament VALUES (22, 'panadol600', '100 mg', 247, 4, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 3, 15);
INSERT INTO public.medicament VALUES (27, 'nom2', '10ml', 96, 6, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 3, 5);
INSERT INTO public.medicament VALUES (20, 'Panadol 19', '1000 mg', 213, 6, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 5, 10);
INSERT INTO public.medicament VALUES (32, 'efrds', '132', 21, 16, true, '2023-02-13', '2023-03-03', '2023-08-02', 'efddsadddddddddd', 2, 1);
INSERT INTO public.medicament VALUES (19, 'Panadol 18', '1000 mg', 546, 5, true, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 4, 6);
INSERT INTO public.medicament VALUES (39, 'no,m', 'ddd', 333, 6, true, '2023-03-23', '2023-09-03', '2023-01-03', 'dwdsad', 4, 15);
INSERT INTO public.medicament VALUES (40, 'med', 'dsad', 22, 8, true, '2023-03-23', '2023-01-03', '2023-02-03', 'dddddas', 3, 17);
INSERT INTO public.medicament VALUES (41, 'med6', 'dsad', 22, 7, true, '2023-03-23', '2023-01-03', '2023-02-03', 'dddddas', 4, 14);
INSERT INTO public.medicament VALUES (42, 'med9', 'dsad', 22, 7, true, '2023-03-23', '2023-03-03', '2023-01-03', 'dddddas', 4, 15);
INSERT INTO public.medicament VALUES (43, 'nnnnnn', 'ooook', 888, 8, true, '2023-03-24', '2023-06-04', '2023-01-04', 'hgf', 4, 19);
INSERT INTO public.medicament VALUES (44, 'fdsa', 'fdsaf', 423, 9, true, '2023-03-27', '2023-03-29', '2023-03-31', 'ffffff', 3, 17);
INSERT INTO public.medicament VALUES (1, 'pananananana', '1000 mg', 78, 1, false, '2023-01-07', '2023-01-07', '2023-01-07', 'some description', 4, 20);


--
-- TOC entry 3420 (class 0 OID 1204725)
-- Dependencies: 212
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.role VALUES (1, 'admin', 'Accès sur tous les fonctionnalité sans exception', '2023-04-05');
INSERT INTO public.role VALUES (3, 'caisse', 'Prend les payement , et les valides', '2023-04-05');
INSERT INTO public.role VALUES (2, 'vendeur', 'Prise et Livraison des commandes sous les flags', '2023-04-05');


--
-- TOC entry 3418 (class 0 OID 1204716)
-- Dependencies: 210
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."user" VALUES (1, 'Laborde', 'Eric', 'eric@eric.com', 'Quatre Bornes', 'eric', 57765698, 'pass', '2023-04-05');
INSERT INTO public."user" VALUES (2, 'Marguerite', 'Charles', 'charles@pharmagest.fr', 'Beau Bassin', 'charles', 57777777, 'test1', '2023-04-07');


--
-- TOC entry 3422 (class 0 OID 1204734)
-- Dependencies: 214
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.user_role OVERRIDING SYSTEM VALUE VALUES (1, 1, 1, '2023-04-05');
INSERT INTO public.user_role OVERRIDING SYSTEM VALUE VALUES (5, 2, 3, '2023-04-13');
INSERT INTO public.user_role OVERRIDING SYSTEM VALUE VALUES (6, 2, 2, '2023-04-13');


--
-- TOC entry 3459 (class 0 OID 0)
-- Dependencies: 227
-- Name: approvisionnement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.approvisionnement_id_seq', 1, false);


--
-- TOC entry 3460 (class 0 OID 0)
-- Dependencies: 225
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.client_id_seq', 1, false);


--
-- TOC entry 3461 (class 0 OID 0)
-- Dependencies: 231
-- Name: commande_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.commande_id_seq', 1, false);


--
-- TOC entry 3462 (class 0 OID 0)
-- Dependencies: 229
-- Name: facture_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.facture_id_seq', 1, false);


--
-- TOC entry 3463 (class 0 OID 0)
-- Dependencies: 217
-- Name: famille_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.famille_id_seq', 1, false);


--
-- TOC entry 3464 (class 0 OID 0)
-- Dependencies: 219
-- Name: forme_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.forme_id_seq', 1, false);


--
-- TOC entry 3465 (class 0 OID 0)
-- Dependencies: 215
-- Name: fournisseur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.fournisseur_id_seq', 1, false);


--
-- TOC entry 3466 (class 0 OID 0)
-- Dependencies: 223
-- Name: fournisseur_prix_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.fournisseur_prix_id_seq', 1, false);


--
-- TOC entry 3467 (class 0 OID 0)
-- Dependencies: 221
-- Name: medicament_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.medicament_id_seq', 1, false);


--
-- TOC entry 3468 (class 0 OID 0)
-- Dependencies: 211
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.role_id_seq', 1, false);


--
-- TOC entry 3469 (class 0 OID 0)
-- Dependencies: 209
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.user_id_seq', 2, true);


--
-- TOC entry 3470 (class 0 OID 0)
-- Dependencies: 213
-- Name: user_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.user_role_id_seq', 6, true);


--
-- TOC entry 3262 (class 2606 OID 1204838)
-- Name: approvisionnement approvisionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.approvisionnement
    ADD CONSTRAINT approvisionnement_pkey PRIMARY KEY (approvisionnement_id);


--
-- TOC entry 3260 (class 2606 OID 1204831)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (client_id);


--
-- TOC entry 3266 (class 2606 OID 1204867)
-- Name: commande commande_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.commande
    ADD CONSTRAINT commande_pkey PRIMARY KEY (commande_id);


--
-- TOC entry 3264 (class 2606 OID 1204855)
-- Name: facture facture_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facture
    ADD CONSTRAINT facture_pkey PRIMARY KEY (facture_id);


--
-- TOC entry 3254 (class 2606 OID 1204769)
-- Name: famille famille_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.famille
    ADD CONSTRAINT famille_pkey PRIMARY KEY (famille_id);


--
-- TOC entry 3256 (class 2606 OID 1204780)
-- Name: forme forme_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.forme
    ADD CONSTRAINT forme_pkey PRIMARY KEY (forme_id);


--
-- TOC entry 3252 (class 2606 OID 1204758)
-- Name: fournisseur fournisseur_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fournisseur
    ADD CONSTRAINT fournisseur_pkey PRIMARY KEY (fournisseur_id);


--
-- TOC entry 3258 (class 2606 OID 1204791)
-- Name: medicament medicament_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.medicament
    ADD CONSTRAINT medicament_pkey PRIMARY KEY (medicament_id);


--
-- TOC entry 3248 (class 2606 OID 1204732)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);


--
-- TOC entry 3246 (class 2606 OID 1204723)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3250 (class 2606 OID 1204738)
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 3269 (class 2606 OID 1204792)
-- Name: medicament FournisseurId; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.medicament
    ADD CONSTRAINT "FournisseurId" FOREIGN KEY (id_fournisseur) REFERENCES public.fournisseur(fournisseur_id);


--
-- TOC entry 3275 (class 2606 OID 1204844)
-- Name: approvisionnement approvisionnement_fournisseur_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.approvisionnement
    ADD CONSTRAINT approvisionnement_fournisseur_id_fkey FOREIGN KEY (fournisseur_id) REFERENCES public.fournisseur(fournisseur_id);


--
-- TOC entry 3274 (class 2606 OID 1204839)
-- Name: approvisionnement approvisionnement_medicament_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.approvisionnement
    ADD CONSTRAINT approvisionnement_medicament_id_fkey FOREIGN KEY (medicament_id) REFERENCES public.medicament(medicament_id);


--
-- TOC entry 3277 (class 2606 OID 1204868)
-- Name: commande commande_facture_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.commande
    ADD CONSTRAINT commande_facture_id_fkey FOREIGN KEY (facture_id) REFERENCES public.facture(facture_id);


--
-- TOC entry 3276 (class 2606 OID 1204856)
-- Name: facture facture_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.facture
    ADD CONSTRAINT facture_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(client_id);


--
-- TOC entry 3271 (class 2606 OID 1204802)
-- Name: medicament familleId; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.medicament
    ADD CONSTRAINT "familleId" FOREIGN KEY (id_famille) REFERENCES public.famille(famille_id);


--
-- TOC entry 3272 (class 2606 OID 1204813)
-- Name: fournisseur_prix fk_fournisseur; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fournisseur_prix
    ADD CONSTRAINT fk_fournisseur FOREIGN KEY (fournisseur_id) REFERENCES public.fournisseur(fournisseur_id);


--
-- TOC entry 3273 (class 2606 OID 1204818)
-- Name: fournisseur_prix fk_medicament; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fournisseur_prix
    ADD CONSTRAINT fk_medicament FOREIGN KEY (medicament_id) REFERENCES public.medicament(medicament_id);


--
-- TOC entry 3270 (class 2606 OID 1204797)
-- Name: medicament formeId; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.medicament
    ADD CONSTRAINT "formeId" FOREIGN KEY (id_forme) REFERENCES public.forme(forme_id);


--
-- TOC entry 3268 (class 2606 OID 1204744)
-- Name: user_role user_role_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(role_id);


--
-- TOC entry 3267 (class 2606 OID 1204739)
-- Name: user_role user_role_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


-- Completed on 2023-04-14 10:24:06

--
-- PostgreSQL database dump complete
--

