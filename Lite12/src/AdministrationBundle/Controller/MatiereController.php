<?php

namespace AdministrationBundle\Controller;

use AdministrationBundle\Entity\Matiere;
use AdministrationBundle\Form\MatiereType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class MatiereController extends Controller
{
    public function ListeAction()
    {   $em = $this->getDoctrine()->getManager();

        $matiereslist = $em->getRepository('AdministrationBundle:Matiere')->findAll();
        return $this->render('@Administration/Matiere/liste.html.twig', array(
            "matieres" => $matiereslist,
        ));
    }
    public function addAction(Request $request)
    {
        $matiere = new Matiere();
        $form = $this->createForm('AdministrationBundle\Form\MatiereType', $matiere);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($matiere);
            $em->flush();

            return $this->redirectToRoute('list', array('id' => $matiere->getId()));
        }


        return $this->render('@Administration/Matiere/add.html.twig', array(
            'matiere' => $matiere,
            'form' => $form->createView(),
        ));
    }

    public function UpdateMAction(Request $request, $id)

    {
        $em=$this->getDoctrine()->getManager();
        $matiere=$em->getRepository("AdministrationBundle:Matiere")->find($id);
        $form=$this->createForm(MatiereType::class,$matiere);
        $form->handleRequest($request);
        if ($form->isSubmitted()){
            $em->persist($matiere);
            $em->flush();
            return $this->redirectToRoute("liste") ;
        }



        return $this->render('@Administration/Matiere/updateM.html.twig', array(
            'form'=>$form->createView()
        ));
    }

    public function DeleteAction($id)
    {
        $matieres = $this -> getDoctrine() -> getRepository(Matiere::class) -> find($id);
        $em = $this -> getDoctrine() -> getManager();
        $em -> remove($matieres);
        $em -> flush();
        return $this -> redirectToRoute("liste");

    }
}
