<?php

namespace activiteBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use activiteBundle\Entity\Questions;
use activiteBundle\Form\QuestionsType;
use activiteBundle\Entity\ReponseEleve;
use Symfony\Component\HttpFoundation\Request;

class QuestionsController extends Controller
{
    public function listeQuestionsAction(){
        $em = $this->getDoctrine()->getManager();
        $questionslist = $em->getRepository('activiteBundle:Questions')->findAll();
        $reponselist = $em->getRepository('activiteBundle:Reponse')->findAll();

        return $this->render('@activite/Questions/listeQuestions.html.twig'
            , array("questions" => $questionslist, "reponseList" =>  $reponselist ,) );
    }
    public function addQuestionAction (Request $request)
    {
        $questions= new Questions();
        $form = $this -> createForm(QuestionsType::class,$questions);
        $form -> handleRequest($request);
        if ( $form -> isSubmitted() ) {
            $em = $this->getDoctrine()->getManager();


            $em->persist($questions);
            $em->flush();

            return $this->redirectToRoute("listeQuestions");
        }
        return $this->render('@activite/Questions/addQuestion.html.twig', array("form"=>$form->createView()));

    }
   public function updateQuestionsAction(Request $request,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $questions= $em->getRepository('activiteBundle:Questions')->find($id);
        $form=$this->createForm(QuestionsType::class,$questions);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($questions);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('listeQuestions');
        }

        return $this->render('@activite/Questions/update_questions.html.twig', array("form"=>$form->createView()));
    }
    public function deleteQuestionsAction($id)
    {
        $questions = $this -> getDoctrine() -> getRepository(questions::class) -> find($id);
        $em = $this -> getDoctrine() -> getManager();
        $em -> remove($questions);
        $em -> flush();
        return $this -> redirectToRoute("listeQuestions");
    }

    public function ListQuizAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $questionslist = $em->getRepository('activiteBundle:Questions')->findOneBy(array("id" => '1'));
        $reponseEleve = new ReponseEleve();
        $reponseEleve->setQuestion($questionslist);


       // $reponselist = $em->getRepository('activiteBundle:Reponse')->findAll();

        $form= $this->createForm(new ReponseEleveType(), $reponseEleve);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($reponseEleve);
            $em->flush();
            $this->addFlash('info', 'Answered !');
            // go to score page
            return $this->redirectToRoute('listeQuestions');
        }


        return $this->render('@activite/Questions/Quizz_old.html.twig', array("form"=>$form->createView()));

/*
        return $this->render('@activite/Questions/Quizz_old.html.twig'
            , array("questions" => $questionslist, "reponseList" =>  $reponselist ,) );
*/
    }
}