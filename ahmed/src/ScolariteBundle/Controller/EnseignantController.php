<?php

namespace ScolariteBundle\Controller;

use ScolariteBundle\Entity\Enseignant;
use ScolariteBundle\Form\EnseignantType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

class EnseignantController extends Controller
{
    function afficherenseignantsAction()
    {
        $em = $this->getDoctrine()->getManager();

        $enseignants = $em->getRepository(Enseignant::class)->findAll();
        return $this->render("afficheenseignants.html.twig", array("enseignants" => $enseignants));

    }

    public function supprimerenseignantsAction($id){
        //the manager is the responsible for saving objects, deleting and updating object
        $em=$this->getDoctrine()->getManager();
        $enseignant=$em->getRepository(Enseignant::class)->findOneByid($id);
        //the remove() method notifies Doctrine that you'd like to remove the given object from the database
        $em->remove($enseignant);
        //The flush() method execute the DELETE query.
        $em->flush();
        //redirect our function to the read page to show our table
        return $this->redirectToRoute('enseignant_homepage');

    }

    public function ajoutenseignantAction(Request $req)
    {
        $enseignant=new Enseignant();
        $form=$this->createForm(EnseignantType::class,$enseignant);
        $form->handleRequest($req);
        if($form->isSubmitted())
        {



            $em=$this->getDoctrine()->getManager();

            $classe=$em->getRepository("ScolariteBundle:classe")->findOneById(1);

            $enseignant->setIdclasse($classe);

            $em->persist($enseignant);
            $em->flush();
            $enseignants=$em->getRepository("ScolariteBundle:Enseignant")->findAll();

            return $this->render("afficheenseignants.html.twig", array("enseignants"=>$enseignants));
        }
        return $this->render("addenseignant.html.twig", array("f"=>$form->createView()));

    }
    public function updateenseignantAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('ScolariteBundle:Enseignant')->find($id);
        $form=$this->createForm(EnseignantType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){

            $em= $this->getDoctrine()->getManager();
            $em->merge($p);
            $em->flush();
            $enseignants=$em->getRepository("ScolariteBundle:Enseignant")->findAll();

            return $this->render("afficheenseignants.html.twig", array("enseignants"=>$enseignants));
        }
        return $this->render("updateenseignant.html.twig", array("f"=>$form->createView()));
    }

}
