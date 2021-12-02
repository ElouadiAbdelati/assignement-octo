package ma.octo.assignement.service;


import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.service.impl.VirementServiceImpl;
import ma.octo.assignement.util.messaging.LocalMessageReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class VirementServiceTest {

    @Mock
    private VirementRepository virementRepository;
    @Mock
    private AuditService auditService;
    @Mock
    private CompteService compteService;
    @Mock
    private LocalMessageReader localMessageReader;
    private VirementServiceImpl virementService;
    private  Compte compteBeneficiaire;
    private Compte compteEmetteur;

    @BeforeEach
    void  init() {
        virementService = new VirementServiceImpl(virementRepository, auditService, compteService, localMessageReader);
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setUsername("user1");
        utilisateur1.setNom("last1");
        utilisateur1.setPrenom("first1");
        utilisateur1.setGenre("Male");

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setUsername("user2");
        utilisateur2.setNom("last2");
        utilisateur2.setPrenom("first2");
        utilisateur2.setGenre("Female");

        compteEmetteur = new Compte();
        compteEmetteur.setNumero("010000A000001001");
        compteEmetteur.setRib("RIB1");
        compteEmetteur.setSolde(BigDecimal.valueOf(200000L));
        compteEmetteur.setUtilisateur(utilisateur1);


        compteBeneficiaire = new Compte();
        compteBeneficiaire.setNumero("010000B025001001");
        compteBeneficiaire.setRib("RIB2");
        compteBeneficiaire.setSolde(BigDecimal.valueOf(140000L));
        compteBeneficiaire.setUtilisateur(utilisateur2);
    }

    @Test
    @DisplayName("virement")
    void save() {

        Result<Compte> result1 = new Result<>();
        result1.setData(compteEmetteur);
        Result<Compte> result2 = new Result<>();
        result2.setData(compteBeneficiaire);

        Mockito.when(compteService
                .findByNumero("010000A000001001")
        ).thenReturn(result1);

        Mockito.when(compteService
                .findByNumero("010000B025001001")
        ).thenReturn(result2);

        Virement virement = new Virement();
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setCompteEmetteur(compteEmetteur);
        virement.setMotif("test");
        virement.setMontant(new BigDecimal(100));

        virementService.save(virement);

        Assertions.assertEquals(0,compteEmetteur.getSolde().compareTo(new BigDecimal(200000 - 100)) );
        Assertions.assertEquals(0,compteBeneficiaire.getSolde().compareTo(new BigDecimal(140000L + 100)));

    }

    @Test
    void  virment(){

        Mockito.when(virementRepository.countByCompteBeneficiairRibAndDateExecution("RIB2",new Date())).thenReturn(11);
    }
}
