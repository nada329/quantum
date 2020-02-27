<?php

namespace activiteBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use activiteBundle\Entity\Questions;
use activiteBundle\Entity\Reponse;
use activiteBundle\Form\ReponseType;

use Symfony\Component\HttpFoundation\Request;
class ReponseController extends Controller
{
    public function addReponseAction(Request $request,$id)
    {

        $reponse= new Reponse();
        $form = $this -> createForm(ReponseType::class,$reponse);
        $form -> handleRequest($request);

        $ema = $this->getDoctrine()->getManager();
        $questions = new Questions();
        $questions= $ema->getRepository('activiteBundle:Questions')->find($id);

        $reponse->setQuestion($questions);
        if ( $form -> isSubmitted() ) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($reponse);
            $em->flush();

            return $this->redirectToRoute("listeQuestions");
        }


        return $this->render('@activite/Reponse/add_reponse.html.twig', array("form"=>$form->createView()));
    }

}
