<?php

namespace activiteBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use activiteBundle\Entity\Cours;
use activiteBundle\Form\CoursType;

use Symfony\Component\HttpFoundation\Request;

class CoursController extends Controller
{
    public function ListCoursAction(){
        $em = $this->getDoctrine()->getManager();

        $courslist = $em->getRepository('activiteBundle:Cours')->findAll();
        return $this->render('@activite/Cours/liste.cour.html.twig', array(
            "scours" => $courslist,
        ));
    }
    public function AddCoursAction(Request $request)
    {
        $cours= new Cours();
        $form = $this -> createForm(CoursType::class,$cours);
        $form -> handleRequest($request);
        if ( $form -> isSubmitted() ) {
            $em = $this->getDoctrine()->getManager();
            $cours->uploadProfilePicture();
            // Current Connected user
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $prof = $user;
            $cours->setProf($prof);
            $em->persist($cours);
            $em->flush();

            return $this->redirectToRoute("list_cours");
        }
        return $this->render('@activite/Cours/add-our.html.twig', array("form"=>$form->createView()));

    }
    public function UpdateCoursAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $cours= $em->getRepository('activiteBundle:Cours')->find($id);
        $form=$this->createForm(CoursType::class,$cours);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($cours);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('list_cours');
        }


        return $this->render('@activite/Cours/update.cour.html.twig', array("form"=>$form->createView()));
    }
    public function DeleteCoursAction($id)
    {
        $cours = $this -> getDoctrine() -> getRepository(Cours::class) -> find($id);
        $em = $this -> getDoctrine() -> getManager();
        $em -> remove($cours);
        $em -> flush();
        return $this -> redirectToRoute("list_cours");
    }
    public function ListCoursEtQuizAction(){
        $em = $this->getDoctrine()->getManager();

        $courslist = $em->getRepository('activiteBundle:Cours')->findAll();
        return $this->render('@activite/Cours/listeCoursFront.html.twig', array(
            "scours" => $courslist,
        ));
    }

}
