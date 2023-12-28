package bg.sofia.uni.fmi.mjt.space.util;

public class TestMockData {

    public static final String MOCK_MISSIONS_DATA = """
            Unnamed: 0,Company Name,Location,Datum,Detail,Status Rocket," Rocket",Status Mission
            0,SpaceX,"LC-39A, Kennedy Space Center, Florida, USA","Fri Aug 07, 2020",Falcon 9 Block 5 | Starlink V1 L9 & BlackSky,StatusActive,"50.0 ",Success
            1,CASC,"Site 9401 (SLS-2), Jiuquan Satellite Launch Center, China","Thu Aug 06, 2020",Long March 2D | Gaofen-9 04 & Q-SAT,StatusActive,"29.75 ",Success
            2,SpaceX,"Pad A, Boca Chica, Texas, USA","Tue Aug 04, 2020",Starship Prototype | 150 Meter Hop,StatusActive,,Success
            3,Roscosmos,"Site 200/39, Baikonur Cosmodrome, Kazakhstan","Thu Jul 30, 2020",Proton-M/Briz-M | Ekspress-80 & Ekspress-103,StatusActive,"65.0 ",Success
            4,ULA,"SLC-41, Cape Canaveral AFS, Florida, USA","Thu Jul 30, 2020",Atlas V 541 | Perseverance,StatusActive,"145.0 ",Success
            5,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Sat Jul 25, 2020","Long March 4B | Ziyuan-3 03, Apocalypse-10 & NJU-HKU 1",StatusActive,"64.68 ",Success
            6,Roscosmos,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Thu Jul 23, 2020",Soyuz 2.1a | Progress MS-15,StatusActive,"48.5 ",Success
            7,CASC,"LC-101, Wenchang Satellite Launch Center, China","Thu Jul 23, 2020",Long March 5 | Tianwen-1,StatusActive,,Success
            8,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Mon Jul 20, 2020",Falcon 9 Block 5 | ANASIS-II,StatusActive,"50.0 ",Success
            9,JAXA,"LA-Y1, Tanegashima Space Center, Japan","Sun Jul 19, 2020",H-IIA 202 | Hope Mars Mission,StatusActive,"90.0 ",Success
            10,Northrop,"LP-0B, Wallops Flight Facility, Virginia, USA","Wed Jul 15, 2020",Minotaur IV | NROL-129,StatusActive,"46.0 ",Success
            11,ExPace,"Site 95, Jiuquan Satellite Launch Center, China","Fri Jul 10, 2020","Kuaizhou 11 | Jilin-1 02E, CentiSpace-1 S2",StatusActive,"28.3 ",Failure
            12,CASC,"LC-3, Xichang Satellite Launch Center, China","Thu Jul 09, 2020",Long March 3B/E | Apstar-6D,StatusActive,"29.15 ",Success
            13,IAI,"Pad 1, Palmachim Airbase, Israel","Mon Jul 06, 2020",Shavit-2 | Ofek-16,StatusActive,,Success
            14,CASC,"Site 9401 (SLS-2), Jiuquan Satellite Launch Center, China","Sat Jul 04, 2020",Long March 2D | Shiyan-6 02,StatusActive,"29.75 ",Success
            15,Rocket Lab,"Rocket Lab LC-1A, M?\u0081hia Peninsula, New Zealand","Sat Jul 04, 2020",Electron/Curie | Pics Or It Didn??¦t Happen,StatusActive,"7.5 ",Failure
            16,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Fri Jul 03, 2020",Long March 4B | Gaofen Duomo & BY-02,StatusActive,"64.68 ",Success
            17,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Tue Jun 30, 2020",Falcon 9 Block 5 | GPS III SV03,StatusActive,"50.0 ",Success
            18,CASC,"LC-2, Xichang Satellite Launch Center, China","Tue Jun 23, 2020",Long March 3B/E | Beidou-3 G3,StatusActive,"29.15 ",Success
            19,CASC,"Site 9401 (SLS-2), Jiuquan Satellite Launch Center, China","Wed Jun 17, 2020","Long March 2D | Gaofen-9 03, Pixing III A & HEAD-5",StatusActive,"29.75 ",Success
            20,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Sat Jun 13, 2020",Falcon 9 Block 5 | Starlink V1 L8 & SkySat 16 to 18,StatusActive,"50.0 ",Success
            21,Rocket Lab,"Rocket Lab LC-1A, M?\u0081hia Peninsula, New Zealand","Sat Jun 13, 2020",Electron/Curie | Don't stop me now!,StatusActive,"7.5 ",Success
            22,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Wed Jun 10, 2020",Long March 2C | Haiyang-1D,StatusActive,"30.8 ",Success
            23,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Thu Jun 04, 2020",Falcon 9 Block 5 | Starlink V1 L7,StatusActive,"50.0 ",Success
            24,CASC,"Site 9401 (SLS-2), Jiuquan Satellite Launch Center, China","Sun May 31, 2020",Long March 2D | Gaofen-9-02 & HEAD-4,StatusActive,"29.75 ",Success
            25,SpaceX,"LC-39A, Kennedy Space Center, Florida, USA","Sat May 30, 2020",Falcon 9 Block 5 | SpaceX Demo-2,StatusActive,"50.0 ",Success
            26,CASC,"Xichang Satellite Launch Center, China","Fri May 29, 2020",Long March 11 | XJS-G and XJS-H,StatusActive,"5.3 ",Success
            27,Virgin Orbit,"Cosmic Girl, Mojave Air and Space Port, California, USA","Mon May 25, 2020",LauncherOne | Demo Flight,StatusActive,"12.0 ",Failure
            28,VKS RF,"Site 43/4, Plesetsk Cosmodrome, Russia","Fri May 22, 2020",Soyuz 2.1b/Fregat-M | Cosmos 2546,StatusActive,,Success
            29,MHI,"LA-Y2, Tanegashima Space Center, Japan","Wed May 20, 2020",H-IIB | HTV-9,StatusRetired,"112.5 ",Success
            30,ULA,"SLC-41, Cape Canaveral AFS, Florida, USA","Sun May 17, 2020",Atlas V 501 | OTV-6 (USSF-7),StatusActive,"120.0 ",Success
            31,ExPace,"Site 95, Jiuquan Satellite Launch Center, China","Tue May 12, 2020",Kuaizhou 1A | Xingyun-2 01 (Wuhan) & 02,StatusActive,,Success
            32,CASC,"LC-101, Wenchang Satellite Launch Center, China","Tue May 05, 2020",Long March 5B | Test Flight (New Crew Capsule),StatusActive,,Success
            33,Roscosmos,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Sat Apr 25, 2020",Soyuz 2.1a | Progress MS-14,StatusActive,"48.5 ",Success
            34,SpaceX,"LC-39A, Kennedy Space Center, Florida, USA","Wed Apr 22, 2020",Falcon 9 Block 5 | Starlink V1 L6,StatusActive,"50.0 ",Success
            35,IRGC,"Launch Plateform, Shahrud Missile Test Site","Wed Apr 22, 2020",Qased | Noor 1,StatusActive,,Success
            36,CASC,"LC-2, Xichang Satellite Launch Center, China","Thu Apr 09, 2020",Long March 3B/E | Nusantara Dua,StatusActive,"29.15 ",Failure
            37,Roscosmos,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Thu Apr 09, 2020",Soyuz 2.1a | Soyuz MS-16,StatusActive,"48.5 ",Success
            38,ULA,"SLC-41, Cape Canaveral AFS, Florida, USA","Thu Mar 26, 2020",Atlas V 551 | AEHF 6,StatusActive,"153.0 ",Success
            39,CASC,"LC-3, Xichang Satellite Launch Center, China","Tue Mar 24, 2020",Long March 2C | Yaogan-30-06,StatusActive,"30.8 ",Success
            40,Arianespace,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Sat Mar 21, 2020",Soyuz 2.1b/Fregat | OneWeb #3,StatusActive,"48.5 ",Success
            41,SpaceX,"LC-39A, Kennedy Space Center, Florida, USA","Wed Mar 18, 2020",Falcon 9 Block 5 | Starlink V1 L5,StatusActive,"50.0 ",Success
            42,VKS RF,"Site 43/4, Plesetsk Cosmodrome, Russia","Mon Mar 16, 2020",Soyuz 2.1b/Fregat-M | Cosmos 2545,StatusActive,,Success
            43,CASC,"LC-201, Wenchang Satellite Launch Center, China","Mon Mar 16, 2020",Long March 7A | XJY-6,StatusActive,,Failure
            44,CASC,"LC-2, Xichang Satellite Launch Center, China","Mon Mar 09, 2020",Long March 3B/E | Beidou-3 G2,StatusActive,"29.15 ",Success
            45,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Sat Mar 07, 2020",Falcon 9 Block 5 | CRS-20,StatusActive,"50.0 ",Success
            46,VKS RF,"Site 43/3, Plesetsk Cosmodrome, Russia","Thu Feb 20, 2020",Soyuz 2.1a/Fregat-M | Meridian-M n†\u00AD19L,StatusActive,"48.5 ",Success
            47,CASC,"LC-3, Xichang Satellite Launch Center, China","Wed Feb 19, 2020",Long March 2D | XJS-C to F,StatusActive,"29.75 ",Success
            48,Arianespace,"ELA-3, Guiana Space Centre, French Guiana, France","Tue Feb 18, 2020",Ariane 5 ECA | JCSAT-17 & GEO-KOMPSAT 2B,StatusActive,"200.0 ",Success
            49,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Mon Feb 17, 2020",Falcon 9 Block 5 | Starlink V1 L4,StatusActive,"50.0 ",Success
            50,Northrop,"LP-0A, Wallops Flight Facility, Virginia, USA","Sat Feb 15, 2020",Antares 230+ | CRS NG-13,StatusActive,"85.0 ",Success
            51,ULA,"SLC-41, Cape Canaveral AFS, Florida, USA","Mon Feb 10, 2020",Atlas V 411 | Solar Orbiter,StatusActive,"115.0 ",Success
            52,ISA,"Imam Khomeini Spaceport, Semnan Space Center, Iran","Sun Feb 09, 2020",Simorgh | Zafar 1,StatusActive,,Failure
            53,MHI,"LA-Y1, Tanegashima Space Center, Japan","Sun Feb 09, 2020",H-IIA 202 | IGS-Optical 7,StatusActive,"90.0 ",Success
            54,Arianespace,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Thu Feb 06, 2020",Soyuz 2.1b/Fregat | OneWeb #2,StatusActive,"48.5 ",Success
            55,Rocket Lab,"Rocket Lab LC-1A, M?\u0081hia Peninsula, New Zealand","Fri Jan 31, 2020",Electron/Curie | Birds of a Feather / NROL-151,StatusActive,"7.5 ",Success
            56,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Wed Jan 29, 2020",Falcon 9 Block 5 | Starlink V1 L3,StatusActive,"50.0 ",Success
            57,SpaceX,"LC-39A, Kennedy Space Center, Florida, USA","Sun Jan 19, 2020",Falcon 9 Block 5 | Crew Dragon Inflight Abort Test,StatusActive,"50.0 ",Success
            58,Arianespace,"ELA-3, Guiana Space Centre, French Guiana, France","Thu Jan 16, 2020",Ariane 5 ECA | Eutelsat Konnect BB4A & GSAT-30,StatusActive,"200.0 ",Success
            59,ExPace,"Site 95, Jiuquan Satellite Launch Center, China","Thu Jan 16, 2020",Kuaizhou 1A | Yinhe-1,StatusActive,,Success
            60,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Wed Jan 15, 2020",Long March 2D | Jilin-1 Wideband 01 & ??uSat-7/8,StatusActive,"29.75 ",Success
            61,CASC,"LC-2, Xichang Satellite Launch Center, China","Tue Jan 07, 2020",Long March 3B/E | TJSW-5,StatusActive,"29.15 ",Success
            62,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Tue Jan 07, 2020",Falcon 9 Block 5 | Starlink V1 L2,StatusActive,"50.0 ",Success
            63,CASC,"LC-101, Wenchang Satellite Launch Center, China","Fri Dec 27, 2019",Long March 5 | Shijian-20,StatusActive,,Success
            64,VKS RF,"Site 133/3, Plesetsk Cosmodrome, Russia","Thu Dec 26, 2019","Rokot/Briz KM | Gonets-M ???24, 25, 26 [block-15] & Blits-M1",StatusRetired,"41.8 ",Success
            65,Roscosmos,"Site 81/24, Baikonur Cosmodrome, Kazakhstan","Tue Dec 24, 2019",Proton-M/DM-3 | Elektro-L n†\u00AD3,StatusActive,"65.0 ",Success
            66,ULA,"SLC-41, Cape Canaveral AFS, Florida, USA","Fri Dec 20, 2019",Atlas V N22 | Starliner OFT,StatusActive,,Success
            67,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Fri Dec 20, 2019","Long March 4B | CBERS-4A, ETRSS-1 & Others",StatusActive,"64.68 ",Success
            68,Arianespace,"ELS, Guiana Space Centre, French Guiana, France","Wed Dec 18, 2019","Soyuz ST-A/Fregat-M | CSG-1, CHEOPS & Others",StatusActive,,Success
            69,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Tue Dec 17, 2019",Falcon 9 Block 5 | JCSAT-18 / Kacific-1,StatusActive,"50.0 ",Success
            70,CASC,"LC-3, Xichang Satellite Launch Center, China","Mon Dec 16, 2019",Long March 3B/YZ-1 | BeiDou-3 M19 & M20,StatusActive,,Success
            71,Blue Origin,"Blue Origin Launch Site, West Texas, Texas, USA","Wed Dec 11, 2019",New Shepard | NS-12,StatusActive,,Success
            72,ISRO,"First Launch Pad, Satish Dhawan Space Centre, India","Wed Dec 11, 2019",PSLV-QL | RISAT 2BR1,StatusActive,"21.0 ",Success
            73,VKS RF,"Site 43/3, Plesetsk Cosmodrome, Russia","Wed Dec 11, 2019",Soyuz 2.1b/Fregat | Cosmos 2544,StatusActive,"48.5 ",Success
            74,ExPace,"Site 9401 (SLS-2), Jiuquan Satellite Launch Center, China","Sat Dec 07, 2019",Kuaizhou 1A | HEAD-2A/B / SpaceTY 16/17 / Tianqi 4A/B,StatusActive,,Success
            75,ExPace,"Taiyuan Satellite Launch Center, China","Sat Dec 07, 2019",Kuaizhou 1A | Jilin-1 Gaofen-02B,StatusActive,,Success
            76,Roscosmos,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Fri Dec 06, 2019",Soyuz 2.1a | Progress MS-13 (74P),StatusActive,"48.5 ",Success
            77,Rocket Lab,"Rocket Lab LC-1A, M?\u0081hia Peninsula, New Zealand","Fri Dec 06, 2019",Electron/Curie | Running Out Of Fingers,StatusActive,"7.5 ",Success
            78,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Thu Dec 05, 2019",Falcon 9 Block 5 | CRS-19,StatusActive,"50.0 ",Success
            79,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Wed Nov 27, 2019",Long March 4C | Gaofen-12,StatusActive,"64.68 ",Success
            80,ISRO,"Second Launch Pad, Satish Dhawan Space Centre, India","Wed Nov 27, 2019",PSLV-XL | Cartosat-3 & Rideshares,StatusActive,"31.0 ",Success
            81,Arianespace,"ELA-3, Guiana Space Centre, French Guiana, France","Tue Nov 26, 2019",Ariane 5 ECA | Inmarsat 5 F5 & TIBA-1,StatusActive,"200.0 ",Success
            82,VKS RF,"Site 43/4, Plesetsk Cosmodrome, Russia","Mon Nov 25, 2019",Soyuz 2.1v/Volga | Cosmos 2542 & 2543,StatusActive,,Success
            83,CASC,"LC-3, Xichang Satellite Launch Center, China","Sat Nov 23, 2019",Long March 3B/YZ-1 | BeiDou-3 M21 & M22,StatusActive,,Success
            84,ExPace,"Site 95, Jiuquan Satellite Launch Center, China","Sun Nov 17, 2019","Kuaizhou 1A | KL-Alpha A, KL-Alpha B",StatusActive,,Success
            85,CASC,"LC-16, Taiyuan Satellite Launch Center, China","Wed Nov 13, 2019",Long March 6 | Ningxia-1 (x5),StatusActive,,Success
            86,ExPace,"Site 95, Jiuquan Satellite Launch Center, China","Wed Nov 13, 2019",Kuaizhou 1A | Jilin 1-02A,StatusActive,,Success
            87,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Mon Nov 11, 2019",Falcon 9 Block 5 | Starlink V1 L1,StatusActive,"50.0 ",Success
            88,CASC,"LC-2, Xichang Satellite Launch Center, China","Mon Nov 04, 2019",Long March 3B/E | Beidou-3 IGSO-3,StatusActive,"29.15 ",Success
            89,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Sun Nov 03, 2019",Long March 4B | Gaofen-7,StatusActive,"64.68 ",Success
            90,Northrop,"LP-0A, Wallops Flight Facility, Virginia, USA","Sat Nov 02, 2019",Antares 230+ | CRS NG-12,StatusActive,"85.0 ",Success
            91,Exos,"Vertical Launch Area, Spaceport America, New Mexico","Sat Oct 26, 2019",SARGE | Launch 4,StatusActive,,Failure
            92,CASC,"LC-3, Xichang Satellite Launch Center, China","Thu Oct 17, 2019",Long March 3B/E | TJSW-4,StatusActive,"29.15 ",Success
            93,Rocket Lab,"Rocket Lab LC-1A, M?\u0081hia Peninsula, New Zealand","Thu Oct 17, 2019",Electron/Curie | As The Crow Flies,StatusActive,"7.5 ",Success
            94,Northrop,"Stargazer, Cape Canaveral AFS, Florida, USA","Fri Oct 11, 2019",Pegasus XL | ICON,StatusActive,"40.0 ",Success
            95,ILS,"Site 200/39, Baikonur Cosmodrome, Kazakhstan","Wed Oct 09, 2019",Proton-M/Briz-M | Eutelsat 5 West B & MEV-1,StatusActive,"65.0 ",Success
            96,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Fri Oct 04, 2019",Long March 4C | Gaofen 10 (Replacement),StatusActive,"64.68 ",Success
            97,VKS RF,"Site 43/4, Plesetsk Cosmodrome, Russia","Thu Sep 26, 2019",Soyuz 2.1b/Fregat | Cosmos 2541,StatusActive,"48.5 ",Success
            98,Roscosmos,"Site 1/5, Baikonur Cosmodrome, Kazakhstan","Wed Sep 25, 2019",Soyuz FG | Soyuz MS-15 (61S),StatusRetired,,Success
            99,CASC,"Site 9401 (SLS-2), Jiuquan Satellite Launch Center, China","Wed Sep 25, 2019",Long March 2D | Yunhai-1-02,StatusActive,"29.75 ",Success
            100,MHI,"LA-Y2, Tanegashima Space Center, Japan","Tue Sep 24, 2019",H-IIB | HTV-8,StatusRetired,"112.5 ",Success
            101,CASC,"LC-2, Xichang Satellite Launch Center, China","Sun Sep 22, 2019",Long March 3B/YZ-1 | BeiDou-3 M23 & M24,StatusActive,,Success
            102,CASC,"Site 95, Jiuquan Satellite Launch Center, China","Thu Sep 19, 2019",Long March 11 | Zhuhai-1 Group 03,StatusActive,"5.3 ",Success
            103,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Thu Sep 12, 2019","Long March 4B | Ziyuan-2D, BNU-1 & Taurus-1",StatusActive,"64.68 ",Success
            104,ExPace,"Site 95, Jiuquan Satellite Launch Center, China","Fri Aug 30, 2019",Kuaizhou 1A | KX-09 & Others,StatusActive,,Success
            105,VKS RF,"Site 133/3, Plesetsk Cosmodrome, Russia","Fri Aug 30, 2019",Rokot/Briz KM | Cosmos 2540,StatusRetired,"41.8 ",Success
            106,ISA,"Imam Khomeini Spaceport, Semnan Space Center, Iran","Thu Aug 29, 2019",Safir-1B+ | Nahid-1,StatusActive,,Prelaunch Failure
            107,ULA,"SLC-37B, Cape Canaveral AFS, Florida, USA","Thu Aug 22, 2019","Delta IV Medium+ (4,2) | GPS III SV02",StatusRetired,"164.0 ",Success
            108,Roscosmos,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Thu Aug 22, 2019",Soyuz 2.1a | Soyuz MS-14 (60S),StatusActive,"48.5 ",Success
            109,Rocket Lab,"Rocket Lab LC-1A, M?\u0081hia Peninsula, New Zealand","Mon Aug 19, 2019","Electron/Curie | Look Ma, No Hands!",StatusActive,"7.5 ",Success
            110,CASC,"LC-2, Xichang Satellite Launch Center, China","Mon Aug 19, 2019",Long March 3B/E | ChinaSat 18,StatusActive,"29.15 ",Success
            111,CASC,"Site 95, Jiuquan Satellite Launch Center, China","Sat Aug 17, 2019",Jielong-1 | Tianqi-4 & Others,StatusActive,"7.5 ",Success
            112,ULA,"SLC-41, Cape Canaveral AFS, Florida, USA","Thu Aug 08, 2019",Atlas V 551 | AEHF 5,StatusActive,"153.0 ",Success
            113,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Tue Aug 06, 2019",Falcon 9 Block 5 | AMOS-17,StatusActive,"50.0 ",Success
            114,Arianespace,"ELA-3, Guiana Space Centre, French Guiana, France","Tue Aug 06, 2019",Ariane 5 ECA | EDRS-C/HYLAS 3 & Intelsat 39,StatusActive,"200.0 ",Success
            115,Roscosmos,"Site 81/24, Baikonur Cosmodrome, Kazakhstan","Mon Aug 05, 2019",Proton-M/Briz-M | Cosmos 2539,StatusActive,"65.0 ",Success
            116,Roscosmos,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Wed Jul 31, 2019",Soyuz 2.1a | Progress MS-12,StatusActive,"48.5 ",Success
            117,VKS RF,"Site 43/4, Plesetsk Cosmodrome, Russia","Tue Jul 30, 2019",Soyuz 2.1a/Fregat-M | Meridian-M n†\u00AD18L,StatusActive,"48.5 ",Success
            118,CASC,"LC-3, Xichang Satellite Launch Center, China","Fri Jul 26, 2019",Long March 2C | Yaogan-30-05,StatusActive,"30.8 ",Success
            119,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Thu Jul 25, 2019",Falcon 9 Block 5 | CRS-18,StatusActive,"50.0 ",Success
            120,i-Space,"Site 95, Jiuquan Satellite Launch Center, China","Thu Jul 25, 2019",Hyperbola-1 | CAS-7B & Others,StatusActive,,Success
            121,ISRO,"Second Launch Pad, Satish Dhawan Space Centre, India","Mon Jul 22, 2019",GSLV Mk III | Chandrayaan-2 lunar mission,StatusActive,"62.0 ",Success
            122,Roscosmos,"Site 1/5, Baikonur Cosmodrome, Kazakhstan","Sat Jul 20, 2019",Soyuz FG | Soyuz MS-13 (59S),StatusRetired,,Success
            123,Roscosmos,"Site 81/24, Baikonur Cosmodrome, Kazakhstan","Sat Jul 13, 2019",Proton-M/DM-3 | Spektr-RG,StatusActive,"65.0 ",Success
            124,Arianespace,"ELV-1 (SLV), Guiana Space Centre, French Guiana, France","Thu Jul 11, 2019",Vega | Falcon Eye 1,StatusActive,"37.0 ",Failure
            125,VKS RF,"Site 43/4, Plesetsk Cosmodrome, Russia","Wed Jul 10, 2019",Soyuz 2.1v/Volga | Cosmos 2535 to 2538,StatusActive,,Success
            126,Roscosmos,"Site 1S, Vostochny Cosmodrome, Russia","Fri Jul 05, 2019","Soyuz 2.1b/Fregat-M | Meteor-M No.2-2, Landmapper-BC 5 & 6, and Others",StatusActive,,Success
            127,Exos,"Vertical Launch Area, Spaceport America, New Mexico","Sat Jun 29, 2019",SARGE | Launch 3,StatusActive,,Partial Failure
            128,Rocket Lab,"Rocket Lab LC-1A, M?\u0081hia Peninsula, New Zealand","Sat Jun 29, 2019",Electron/Curie | Make it Rain,StatusActive,"7.5 ",Success
            129,SpaceX,"LC-39A, Kennedy Space Center, Florida, USA","Tue Jun 25, 2019",Falcon Heavy | STP-2,StatusActive,"90.0 ",Success
            130,CASC,"LC-3, Xichang Satellite Launch Center, China","Mon Jun 24, 2019",Long March 3B/E | Beidou-3 IGSO-2,StatusActive,"29.15 ",Success
            131,Arianespace,"ELA-3, Guiana Space Centre, French Guiana, France","Thu Jun 20, 2019",Ariane 5 ECA | Eutelsat 7C &  AT&T T-16,StatusActive,"200.0 ",Success
            132,SpaceX,"SLC-4E, Vandenberg AFB, California, USA","Wed Jun 12, 2019",Falcon 9 Block 5 | RADARSAT Constellation,StatusActive,"50.0 ",Success
            133,CASC,"Tai Rui Barge, Yellow Sea","Wed Jun 05, 2019",Long March 11H | Jilin-1 & Others,StatusActive,"5.3 ",Success
            134,Roscosmos,"Site 200/39, Baikonur Cosmodrome, Kazakhstan","Thu May 30, 2019",Proton-M/Briz-M | Yamal-601,StatusActive,"65.0 ",Success
            135,Roscosmos,"Site 43/4, Plesetsk Cosmodrome, Russia","Mon May 27, 2019",Soyuz 2.1b/Fregat-M | Cosmos 2534,StatusActive,,Success
            136,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Fri May 24, 2019",Falcon 9 Block 5 | Starlink V0.9,StatusActive,"50.0 ",Success
            137,CASC,"LC-9, Taiyuan Satellite Launch Center, China","Wed May 22, 2019",Long March 4C | Yaogan Weixing-33,StatusActive,"64.68 ",Failure
            138,ISRO,"First Launch Pad, Satish Dhawan Space Centre, India","Wed May 22, 2019",PSLV-CA | RISAT-2B,StatusActive,"21.0 ",Success
            139,CASC,"LC-2, Xichang Satellite Launch Center, China","Fri May 17, 2019",Long March 3C/E | Beidou-2 G8,StatusActive,,Success
            140,Rocket Lab,"Rocket Lab LC-1A, M?\u0081hia Peninsula, New Zealand","Sun May 05, 2019",Electron/Curie | That's a Funny Looking Cactus,StatusActive,"7.5 ",Success
            141,SpaceX,"SLC-40, Cape Canaveral AFS, Florida, USA","Sat May 04, 2019",Falcon 9 Block 5 | CRS-17,StatusActive,"50.0 ",Success
            142,Blue Origin,"Blue Origin Launch Site, West Texas, Texas, USA","Thu May 02, 2019",New Shepard | NS-11,StatusActive,,Success
            143,CASC,"Taiyuan Satellite Launch Center, China","Mon Apr 29, 2019",Long March 4B | Tianhui-2 Group 01,StatusActive,"64.68 ",Success
            144,CASC,"LC-3, Xichang Satellite Launch Center, China","Sat Apr 20, 2019",Long March 3B/E | Beidou-3 IGSO-1,StatusActive,"29.15 ",Success
            145,Northrop,"LP-0A, Wallops Flight Facility, Virginia, USA","Wed Apr 17, 2019",Antares 230 | CRS NG-11,StatusRetired,"85.0 ",Success
            146,SpaceX,"LC-39A, Kennedy Space Center, Florida, USA","Thu Apr 11, 2019",Falcon Heavy | ArabSat 6A,StatusActive,"90.0 ",Success
            147,Arianespace,"ELS, Guiana Space Centre, French Guiana, France","Thu Apr 04, 2019",Soyuz ST-B/Fregat-MT | O3b FM17-FM20,StatusActive,,Success
            148,Roscosmos,"Site 31/6, Baikonur Cosmodrome, Kazakhstan","Thu Apr 04, 2019",Soyuz 2.1a | Progress MS-11 (72P),StatusActive,"48.5 ",Success
            149,ISRO,"Second Launch Pad, Satish Dhawan Space Centre, India","Mon Apr 01, 2019",PSLV-QL | EMISAT & Rideshares,StatusActive,"21.0 ",Success
            150,CASC,"LC-2, Xichang Satellite Launch Center, China","Sun Mar 31, 2019",Long March 3B/E | Tianlian-2,StatusActive,"29.15 ",Success""";

    public static final String MOCK_ROCKETS_DATA = """
            "",Name,Wiki,Rocket Height
            0,Tsyklon-3,https://en.wikipedia.org/wiki/Tsyklon-3,39.0 m
            1,Tsyklon-4M,https://en.wikipedia.org/wiki/Cyclone-4M,38.7 m
            2,Unha-2,https://en.wikipedia.org/wiki/Unha,28.0 m
            3,Unha-3,https://en.wikipedia.org/wiki/Unha,32.0 m
            4,Vanguard,https://en.wikipedia.org/wiki/Vanguard_(rocket),23.0 m
            5,Vector-H,https://en.wikipedia.org/wiki/Vector-H,18.3 m
            6,Vector-R,https://en.wikipedia.org/wiki/Vector-R,13.0 m
            7,Vega,https://en.wikipedia.org/wiki/Vega_(rocket),29.9 m
            8,Vega C,https://en.wikipedia.org/wiki/Vega_(rocket),35.0 m
            9,Vega E,https://en.wikipedia.org/wiki/Vega_(rocket),35.0 m
            10,VLS-1,https://en.wikipedia.org/wiki/VLS-1,19.0 m
            11,Volna,https://en.wikipedia.org/wiki/Volna,15.0 m
            12,Voskhod,https://en.wikipedia.org/wiki/Voskhod_(rocket),31.0 m
            13,Vostok,https://en.wikipedia.org/wiki/Vostok-K,31.0 m
            14,Vostok-2,https://en.wikipedia.org/wiki/Vostok-2_(rocket),
            15,Vostok-2A,https://en.wikipedia.org/wiki/Vostok_(rocket_family),
            16,Vostok-2M,https://en.wikipedia.org/wiki/Vostok-2M,
            17,Vulcan Centaur,https://en.wikipedia.org/wiki/Vulcan_%28rocket%29,58.3 m
            18,Zenit-2,https://en.wikipedia.org/wiki/Zenit-2,57.0 m
            19,Zenit-2 FG,https://en.wikipedia.org/wiki/Zenit_%28rocket_family%29,57.0 m
            20,Zenit-3 SL,https://en.wikipedia.org/wiki/Zenit_%28rocket_family%29,59.6 m
            21,Zenit-3 SLB,https://en.wikipedia.org/wiki/Zenit_%28rocket_family%29,57.0 m
            22,Zenit-3 SLBF,https://en.wikipedia.org/wiki/Zenit-3F,57.0 m
            23,Zéphyr,https://fr.wikipedia.org/wiki/Z%C3%A9phyr_(fus%C3%A9e),12.3 m
            24,ZhuQue-1,https://en.wikipedia.org/wiki/LandSpace,19.0 m
            25,ZhuQue-2,https://en.wikipedia.org/wiki/LandSpace#Zhuque-2,
            26,Angara 1.1,https://en.wikipedia.org/wiki/Angara_(rocket_family),35.0 m
            27,Angara 1.2,https://en.wikipedia.org/wiki/Angara_(rocket_family),41.5 m
            28,Angara A5/Briz-M,https://en.wikipedia.org/wiki/Angara_(rocket_family)#Angara_A5,
            29,Angara A5/DM-03,https://en.wikipedia.org/wiki/Angara_(rocket_family)#Angara_A5,
            30,Angara A5M,https://en.wikipedia.org/wiki/Angara_(rocket_family)#Angara_A5,
            31,Antares 110,https://en.wikipedia.org/wiki/Antares_(rocket),40.5 m
            32,Antares 120,https://en.wikipedia.org/wiki/Antares_(rocket),40.5 m
            33,Antares 130,https://en.wikipedia.org/wiki/Antares_(rocket),40.5 m
            34,Antares 230,https://en.wikipedia.org/wiki/Antares_(rocket),41.9 m
            35,Antares 230+,https://en.wikipedia.org/wiki/Antares_%28rocket%29#Antares_230+,42.5 m
            36,Ares 1-X,https://en.wikipedia.org/wiki/Ares_I,94.0 m
            37,Ariane 1,https://en.wikipedia.org/wiki/Ariane_1,50.0 m
            38,Ariane 2,https://en.wikipedia.org/wiki/Ariane_2,49.13 m
            39,Ariane 3,https://en.wikipedia.org/wiki/Ariane_3,49.13 m
            40,Ariane 40,https://en.wikipedia.org/wiki/Ariane_4,58.72 m
            41,Ariane 42L,https://en.wikipedia.org/wiki/Ariane_4,58.72 m
            42,Ariane 42P,https://en.wikipedia.org/wiki/Ariane_4,58.72 m
            43,Ariane 44L,https://en.wikipedia.org/wiki/Ariane_4,58.72 m
            44,Ariane 44LP,https://en.wikipedia.org/wiki/Ariane_4,58.72 m
            45,Ariane 44P,https://en.wikipedia.org/wiki/Ariane_4,58.72 m
            46,Ariane 5 ECA,https://en.wikipedia.org/wiki/Ariane_5,53.0 m
            47,Ariane 5 ES,https://en.wikipedia.org/wiki/Ariane_5,50.5 m
            48,Ariane 5 G,https://en.wikipedia.org/wiki/Ariane_5,52.0 m
            49,Ariane 5 G+,https://en.wikipedia.org/wiki/Ariane_5,
            50,Ariane 5 GS,https://en.wikipedia.org/wiki/Ariane_5,
            51,Ariane 62,https://en.wikipedia.org/wiki/Ariane_6,63.0 m
            52,Ariane 64,https://en.wikipedia.org/wiki/Ariane_6,63.0 m
            53,Ariane 64 / Icarus,https://en.wikipedia.org/wiki/Ariane_6,63.0 m
            54,ASLV,https://en.wikipedia.org/wiki/Augmented_Satellite_Launch_Vehicle,24.0 m
            55,Athena I,https://en.wikipedia.org/wiki/Athena_I,18.9 m
            56,Athena II,https://en.wikipedia.org/wiki/Athena_II,28.2 m
            57,Atlas-D Able,https://en.wikipedia.org/wiki/Atlas-Able,35.0 m
            58,Atlas-D Mercury,https://en.wikipedia.org/wiki/Atlas_LV-3B,28.7 m
            59,Atlas-D OV1,https://en.wikipedia.org/wiki/SM-65D_Atlas,
            60,Atlas-E/F Agena D,https://en.wikipedia.org/wiki/Atlas-Agena,
            61,Atlas-E/F Altair,https://en.wikipedia.org/wiki/Atlas_E/F,
            62,Atlas-E/F Burner,,
            63,Atlas-E/F MSD,https://en.wikipedia.org/wiki/Atlas_E/F#Atlas_E/F-MSD,
            64,Atlas-E/F OIS,https://en.wikipedia.org/wiki/Atlas_E/F#Atlas_E/F-OIS,
            65,Atlas-E/F OV1,https://en.wikipedia.org/wiki/Atlas_E/F#Atlas_E/F-OV1,
            66,Atlas-E/F PTS,https://en.wikipedia.org/wiki/Atlas_E/F#Atlas_E/F-PTS,
            67,Atlas-E/F SGS-1,https://en.wikipedia.org/wiki/Atlas_E/F#Atlas_E/F-SGS,
            68,Atlas-E/F SGS-2,https://en.wikipedia.org/wiki/Atlas_E/F#Atlas_E/F-SGS,
            69,Atlas-E/F Star-17A,https://en.wikipedia.org/wiki/Atlas_E/F#Atlas_E/F-Star,
            70,Atlas-E/F Star-37S-ISS,https://en.wikipedia.org/wiki/Atlas_E/F#Atlas_E/F-Star,
            71,Atlas-G Centaur-D1AR,https://en.wikipedia.org/wiki/Atlas_G,
            72,Atlas-H MSD,https://en.wikipedia.org/wiki/Atlas_H,
            73,Atlas I,https://en.wikipedia.org/wiki/Atlas_I,43.9 m
            74,Atlas II,https://en.wikipedia.org/wiki/List_of_Atlas_launches_(1990–1999),47.5 m
            75,Atlas IIA,https://en.wikipedia.org/wiki/List_of_Atlas_launches_(1990–1999),47.5 m
            76,Atlas IIAS,https://en.wikipedia.org/wiki/List_of_Atlas_launches_(1990–1999),47.5 m
            77,Atlas IIIA,https://en.wikipedia.org/wiki/Atlas_III,52.8 m
            78,Atlas IIIB,https://en.wikipedia.org/wiki/Atlas_III,52.8 m
            79,Atlas-LV3 Agena-A,https://en.wikipedia.org/wiki/Atlas-Agena,
            80,Atlas-LV3 Agena-B,https://en.wikipedia.org/wiki/Atlas-Agena,
            81,Atlas-LV3 Agena-D,https://en.wikipedia.org/wiki/Atlas-Agena,
            82,Atlas-LV3C Centaur-A,https://en.wikipedia.org/wiki/Atlas-Centaur,
            83,Atlas-LV3C Centaur-B,https://en.wikipedia.org/wiki/Atlas-Centaur,
            84,Atlas-LV3C Centaur-C,https://en.wikipedia.org/wiki/Atlas-Centaur,
            85,Atlas-LV3C Centaur-D,https://en.wikipedia.org/wiki/Atlas-Centaur,
            86,Atlas SLV-3,https://en.wikipedia.org/wiki/Atlas_SLV-3,
            87,Atlas-SLV3A Agena-D,https://en.wikipedia.org/wiki/Atlas-Agena,
            88,Atlas-SLV3 Agena-B,https://en.wikipedia.org/wiki/Atlas-Agena,
            89,Atlas-SLV3 Agena-D,https://en.wikipedia.org/wiki/Atlas-Agena,
            90,Atlas-SLV3B Agena-D,https://en.wikipedia.org/wiki/Atlas-Agena,
            91,Atlas-SLV3 Burner-2,https://en.wikipedia.org/wiki/Atlas_SLV-3,
            92,Atlas-SLV3C Centaur-D,https://en.wikipedia.org/wiki/Atlas-Centaur,
            93,Atlas-SLV3D Centaur-D1A,https://en.wikipedia.org/wiki/Atlas-Centaur,
            94,Atlas-SLV3D Centaur-D1AR,https://en.wikipedia.org/wiki/Atlas-Centaur,
            95,Atlas V 401,https://en.wikipedia.org/wiki/Atlas_V,58.3 m
            96,Atlas V 411,https://en.wikipedia.org/wiki/Atlas_V,58.3 m
            97,Atlas V 421,https://en.wikipedia.org/wiki/Atlas_V,58.3 m
            98,Atlas V 431,https://en.wikipedia.org/wiki/Atlas_V,59.1 m
            99,Atlas V 501,https://en.wikipedia.org/wiki/Atlas_V,62.2 m
            100,Atlas V 511,https://en.wikipedia.org/wiki/Atlas_V,62.2 m
            101,Atlas V 521,https://en.wikipedia.org/wiki/Atlas_V,62.2 m
            102,Atlas V 531,https://en.wikipedia.org/wiki/Atlas_V,62.2 m
            103,Atlas V 541,https://en.wikipedia.org/wiki/Atlas_V,62.2 m
            104,Atlas V 551,https://en.wikipedia.org/wiki/Atlas_V,62.2 m
            105,Atlas V 552,https://en.wikipedia.org/wiki/Atlas_V,62.2 m
            106,Atlas V N22,https://en.wikipedia.org/wiki/Atlas_V,
            107,Black Arrow,https://en.wikipedia.org/wiki/Black_Arrow,13.0 m
            108,Blue Scout II,https://en.wikipedia.org/wiki/RM-90_Blue_Scout_II,24.0 m
            109,Ceres-1,,19.0 m
            110,Commercial Titan III,https://en.wikipedia.org/wiki/Commercial_Titan_III,47.0 m
            111,Conestoga-1620,https://en.wikipedia.org/wiki/Conestoga_(rocket),
            112,Cosmos-1 (65S3),https://en.wikipedia.org/wiki/Kosmos_(rocket_family),31.0 m
            113,Cosmos-2I (63S1),https://en.wikipedia.org/wiki/Kosmos_(rocket_family),31.0 m
            114,Cosmos-2I (63SM),https://en.wikipedia.org/wiki/Kosmos-2I,31.0 m
            115,Cosmos-3 (11K65),https://en.wikipedia.org/wiki/Kosmos-3,26.0 m
            116,Cosmos-3M (11K65M),https://en.wikipedia.org/wiki/Kosmos-3M,32.0 m
            117,Cosmos-3MRB (65MRB),https://en.wikipedia.org/wiki/Kosmos-3,26.0 m
            118,Delta 3920-8,https://en.wikipedia.org/wiki/Delta_3000,
            119,Delta 4925-8,https://en.wikipedia.org/wiki/Delta_(rocket_family)#Delta_4000-Series,34.0 m
            120,Delta A,https://en.wikipedia.org/wiki/Delta_A,
            121,Delta B,https://en.wikipedia.org/wiki/Delta_B,
            122,Delta C,https://en.wikipedia.org/wiki/Delta_C,31.0 m
            123,Delta II 6920-10,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            124,Delta II 6920-8,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            125,Delta II 6925,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            126,Delta II 6925-8,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            127,Delta II 7320-10C,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            128,Delta II 7326,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            129,Delta II 7420-10C,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            130,Delta II 7425,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            131,Delta II 7425-10C,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            132,Delta II 7426,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            133,Delta II 7920-10,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            134,Delta II 7920-10C,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            135,Delta II 7920-10L,https://en.wikipedia.org/wiki/Delta_II,39.3 m
            136,Delta II 7920-8,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            137,Delta II 7920H,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            138,Delta II 7920H-10C,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            139,Delta II 7925,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            140,Delta II 7925-10,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            141,Delta II 7925-10C,https://en.wikipedia.org/wiki/Delta_II,38.9 m
            142,Delta II 7925-10L,https://en.wikipedia.org/wiki/Delta_II,39.3 m
            143,Delta II 7925-8,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            144,Delta II 7925H,https://en.wikipedia.org/wiki/Delta_II,38.1 m
            145,Delta III 8930,https://en.wikipedia.org/wiki/Delta_III,35.0 m
            146,Delta IV Heavy,https://en.wikipedia.org/wiki/Delta_IV_Heavy,72.0 m
            147,Delta IV Medium,https://en.wikipedia.org/wiki/Delta_(rocket_family)#Delta_M,62.5 m
            148,"Delta IV Medium+ (4,2)",https://en.wikipedia.org/wiki/Delta_IV,62.5 m
            149,"Delta IV Medium+ (5,2)",https://en.wikipedia.org/wiki/Delta_IV,66.4 m
            150,"Delta IV Medium+ (5,4)",https://en.wikipedia.org/wiki/Delta_IV,66.4 m""";


    public static final String ENCRYPTION_MOCK_MISSION = """
            Unnamed: 0,Company Name,Location,Datum,Detail,Status Rocket," Rocket",Status Mission
            0,SpaceX,"LC-39A, Kennedy Space Center, Florida, USA","Fri Aug 07, 2020",Falcon 9 Block 5 | Starlink V1 L9 & BlackSky,StatusActive,"50.0 ",Success
            1,CASC,"Site 9401 (SLS-2), Jiuquan Satellite Launch Center, China","Thu Aug 06, 2020",Long March 2D | Gaofen-9 04 & Q-SAT,StatusActive,"29.75 ",Success
            2,SpaceX,"Pad A, Boca Chica, Texas, USA","Tue Aug 04, 2020",Starship Prototype | 150 Meter Hop,StatusActive,,Success""";
    public static final String ENCRYPTION_MOCK_ROCKET = """
            "",Name,Wiki,Rocket Height
            169,Falcon 9 Block 5,https://en.wikipedia.org/wiki/Falcon_9,70.0 m
            213,Long March 2D,https://en.wikipedia.org/wiki/Long_March_2D,41.06 m
            371,Starship Prototype,https://en.wikipedia.org/wiki/SpaceX_Starship,50.0 m""";
}
