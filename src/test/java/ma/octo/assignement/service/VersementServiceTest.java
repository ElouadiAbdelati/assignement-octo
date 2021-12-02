package ma.octo.assignement.service;


import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.service.impl.VersementServiceImpl;
import ma.octo.assignement.service.impl.VirementServiceImpl;
import ma.octo.assignement.util.messaging.LocalMessageReader;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class VersementServiceTest {
    @Mock
    private  VersementRepository versementRepository;
    @Mock
    private  AuditService auditService;
    @Mock
    private  CompteService compteService;
    @Mock
    private  LocalMessageReader localMessageReader;

    private VersementService versementService;
    private  Compte compteBeneficiaire;

    @BeforeEach
    void  init(){
        versementService = new VersementServiceImpl(versementRepository, auditService, compteService, localMessageReader);
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

        compteBeneficiaire = new Compte();
        compteBeneficiaire.setNumero("010000A000001000");
        compteBeneficiaire.setRib("RIB1");
        compteBeneficiaire.setSolde(BigDecimal.valueOf(200000L));
        compteBeneficiaire.setUtilisateur(utilisateur1);

    }
    @Test
    @DisplayName("versement")
    void save() {

        Result<Compte> result1 =  new Result<>();
        result1.setData(compteBeneficiaire);

        Mockito.when(compteService
                .findByNumero("010000A000001000")
                ).thenReturn(result1);
        Versement versement = new Versement();
        versement.setPrenomEmetteur("Abdelati");
        versement.setNomEmetteur("EL OUADI");
        versement.setCompteBeneficiaire(compteBeneficiaire);
        versement.setMotif("test");
        versement.setMontant(new BigDecimal(100));
        versementService.save(versement);

        Assertions.assertEquals(0,compteBeneficiaire.getSolde().compareTo(new BigDecimal(200000 + 100)));

    }
}
