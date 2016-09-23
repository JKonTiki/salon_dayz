--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE clients (
    id integer NOT NULL,
    info character varying,
    stylistid integer,
    name character varying
);


ALTER TABLE clients OWNER TO "Guest";

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO "Guest";

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE stylists (
    id integer NOT NULL,
    info character varying,
    name character varying
);


ALTER TABLE stylists OWNER TO "Guest";

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO "Guest";

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY clients (id, info, stylistid, name) FROM stdin;
1	lhomme dombre, il a des idees perfides	1	The Duke of Buckingham
2	ccccc	23	ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc
3	ccccccccccccccc	23	cccccccccccccccccccc
4	aaa	23	aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
5	nom	23	information
6	bbbbbbbbbb	24	bbbb
9	iiiiiii	25	888
10	success?	25	hhh
11	asdfsa	26	asdfsa
12	Buckingham se retira de la vie publique après l’accession au trône du catholique Jacques II, et retourna à son manoir de Helmsley dans le Yorkshire, sans doute en raison du mauvais état de sa santé et de ses finances. Il publia en 1685 un pamphlet intitulé A short Discourse on the Reasonableness of Man’s having a Religion : ce texte prône la tolérance religieuse et fut soutenu, entre autres, par William Penn. Par espoir de le convertir au catholicisme, Jacques II lui envoya un prêtre, dont Buckingham tourna les arguments en ridicule. Le duc s’éteignit le 16 avril 1687 des suites d’un rhume attrapé lors d’une chasse, en exprimant selon Alexander Pope un profond repentir et en se sentant « méprisé par mon pays et, je le crains, abandonné par mon Dieu ». Ce tableau misérabiliste est sans doute grandement exagéré.	27	Le duc de Buckingham
13	En 1666, il est nommé « Capitaine des petits chiens du Roi courant le chevreuil » (charge qui lui rapporte des gages et lui assure un logement à Versailles) ; il se démet de cette charge en 1667 pour devenir capitaine-lieutenant de la première compagnie des mousquetaires, ce qui lui assure une solde de neuf cents livres par mois.	27	D'Artagnan
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('clients_id_seq', 13, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY stylists (id, info, name) FROM stdin;
27	Au traditionnel titre de Dauphin de Viennois est ajouté à sa naissance celui de Premier fils de France. L’enfant est aussi prénommé Louis-Dieudonné, car, après presque vingt-trois ans de mariage sans enfant, plusieurs fausses couches de la reine et la mésentente du couple royal, la naissance inattendue de l’héritier du trône est considérée comme un don du Ciel, au point que certains historiens suspectent le véritable père d’être Mazarin dont des lettres échangées avec Anne d'Autriche, utilisant des codes, sont parfois très sentimentales. Beaucoup de courtisans parlent de « miracle ». En effet en 1637, Louis XIII avait présenté un acte faisant de la Vierge Marie la « protectrice spéciale » de son royaume et, à peine la grossesse de la reine est-elle avérée que le roi publie cet acte le 10 février 1638, la fête de l'Assomption de Marie célébrée par le Vœu de Louis XIII étant désormais un jour férié et chômé en France. Irrité, Louis XIII aurait répliqué que « ce n'était point là si grand miracle qu'un mari couchât avec sa femme et lui fasse un enfant . Des récits affirment que le roi a été conçu le 5 décembre 1637Note 1, l'historien Jean-Christian Petitfils propose plutôt la date du 20 ou du 30 novembre10.\r\n\r\n	Lousi XIV
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('stylists_id_seq', 27, true);


--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

