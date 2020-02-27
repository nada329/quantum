<?php

namespace ScolariteBundle\Controller;

use ScolariteBundle\Entity\Eleve;
use ScolariteBundle\Form\EleveType;
use ScolariteBundle\ScolariteBundle;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

class EleveController extends Controller
{
    function afficherelevesAction()
    {
        $em = $this->getDoctrine()->getManager();

        $eleves = $em->getRepository("ScolariteBundle:Eleve")->findAll();
        return $this->render("afficheeleves.html.twig", array("eleves" => $eleves));

    }

    public function supprimerelevesAction($id){
        //the manager is the responsible for saving objects, deleting and updating object
        $em=$this->getDoctrine()->getManager();
        $eleve=$em->getRepository(Eleve::class)->findOneByid($id);
        //the remove() method notifies Doctrine that you'd like to remove the given object from the database
        $em->remove($eleve);
        //The flush() method execute the DELETE query.
        $em->flush();
        //redirect our function to the read page to show our table
        return $this->redirectToRoute('scolarite_homepage');

    }

    public function ajouteleveAction(Request $req)
    {
        $eleve=new Eleve();
        $form=$this->createForm(EleveType::class,$eleve);
        $form->handleRequest($req);
        if($form->isSubmitted())
        {

            $em=$this->getDoctrine()->getManager();
            // $classe=$em->getRepository("ScolariteBundle:classe")->findOneById(1);
            //  $eleve->setIdclasse($classe);

            $em->persist($eleve);
            $em->flush();
            $eleves=$em->getRepository("ScolariteBundle:Eleve")->findAll();

            return $this->render("afficheeleves.html.twig", array("eleves"=>$eleves));
        }
        return $this->render("addeleve.html.twig", array("f"=>$form->createView()));

    }
    public function updateeleveAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $p= $em->getRepository('ScolariteBundle:Eleve')->find($id);
        $form=$this->createForm(EleveType::class,$p);
        $form->handleRequest($request);
        if($form->isSubmitted()){

            $em= $this->getDoctrine()->getManager();
            $em->merge($p);
            $em->flush();
            $eleves=$em->getRepository("ScolariteBundle:Eleve")->findAll();

            return $this->render("afficheeleves.html.twig", array("eleves"=>$eleves));
        }
        return $this->render("updateeleve.html.twig", array("f"=>$form->createView()));
    }

    public function rechercheeleveAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $eleves=$em->getRepository("ScolariteBundle:Eleve")->findAll();
        if ($request->isMethod('POST')){
            $nom=$request->get('nom');
            $eleves=$em->getRepository("ScolariteBundle:Eleve")->findBy(array("nom"=>$nom));

        }
        return $this->render("rech.html.twig",array('eleves'=>$eleves));
    }



}
