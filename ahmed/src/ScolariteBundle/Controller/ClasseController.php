<?php

namespace ScolariteBundle\Controller;

use ScolariteBundle\Entity\classe;
use ScolariteBundle\Entity\Eleve;
use ScolariteBundle\Form\classeType;
use ScolariteBundle\Form\EleveType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class ClasseController extends Controller
{
    function afficheclasseAction()
    {
        $em = $this->getDoctrine()->getManager();

        $classe = $em->getRepository(classe::class)->findAll();
        return $this->render("afficheclasse.html.twig", array("classe" => $classe));

    }



    public function ajoutclasseAction(Request $req)
    {
        $classe=new classe();
        $form=$this->createForm(classeType::class,$classe);
        $form->handleRequest($req);
        if($form->isSubmitted())
        {



            $em=$this->getDoctrine()->getManager();
            $c=$em->getRepository("ScolariteBundle:classe")->findOneById(1);


            $em->persist($classe);
            $em->flush();
            $classe=$em->getRepository("ScolariteBundle:classe")->findAll();

            return $this->render("afficheclasse.html.twig", array("classe"=>$classe));
        }
        return $this->render("addclasse.html.twig", array("f"=>$form->createView()));

    }

    public function supprimeclasseAction($id){
        //the manager is the responsible for saving objects, deleting and updating object
        $em=$this->getDoctrine()->getManager();
        $classe=$em->getRepository(classe::class)->findOneByid($id);
        //the remove() method notifies Doctrine that you'd like to remove the given object from the database
        $em->remove($classe);
        //The flush() method execute the DELETE query.
        $em->flush();
        //redirect our function to the read page to show our table
        return $this->redirectToRoute('classe_homepage');

    }


    public function updateclasseAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('ScolariteBundle:classe')->find($id);
        $form=$this->createForm(classeType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){

            $em= $this->getDoctrine()->getManager();
            $em->merge($p);
            $em->flush();
            $classe=$em->getRepository("ScolariteBundle:classe")->findAll();

            return $this->render("afficheclasse.html.twig", array("classe"=>$classe));
        }
        return $this->render("updateclasse.html.twig", array("f"=>$form->createView()));
    }


}
