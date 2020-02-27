<?php

namespace activiteBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use activiteBundle\Entity\Questions;
use activiteBundle\Form\QuestionsType;
use activiteBundle\Form\ReponseType;
use activiteBundle\Form\ReponseEleveType;
use activiteBundle\Entity\ReponseEleve;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Knp\Snappy\Pdf;

class ReponseEleveController extends Controller
{
    public function ListQuizAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $questList = $em->getRepository('activiteBundle:Questions')->findAll();
        $reponseEleve = new ReponseEleve();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();

        $reponseEleve->setEleve( $user );

        $resultList = $em->getRepository('activiteBundle:ReponseEleve')->findBy(array("eleve" => $reponseEleve->getEleve()->getId()));

        if($request->request->get('reponse_id')){
            $reponseEleve->setQuestion($em->getRepository('activiteBundle:Questions')->find($request->request->get('question_id')));
            $reponseEleve->setReponse($em->getRepository('activiteBundle:Reponse')->find($request->request->get('reponse_id')));
            if($reponseEleve->getReponse()->getValeur() == 'correct')
                $reponseEleve->setScore(10);
            else
                $reponseEleve->setScore(0);

            $em = $this->getDoctrine()->getManager();
            if($em->getRepository('activiteBundle:ReponseEleve')->findBy(
                array("question" => $reponseEleve->getQuestion()->getId() , "eleve" => $reponseEleve->getEleve()->getId())) == null)
            { $em->persist($reponseEleve); }
            else
                $em->merge($reponseEleve);
            $em->flush();
            $this->addFlash('info', 'Answered !');
            $arrData = ['question_id' => $request->request->get('question_id') , 'reponse_id' => $request->request->get('reponse_id') ];
            return new JsonResponse(json_encode($arrData));
        }


        if($resultList != null)
            return $this -> redirectToRoute("quizresult");


        return $this->render('@activite/Questions/Quizz.html.twig', array( "questionList" => $questList));

    }

    public function QuizResultAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $reponseEleve = new ReponseEleve();
        $reponseEleve->setEleve( $this->container->get('security.token_storage')->getToken()->getUser());
        $resultList = $em->getRepository('activiteBundle:ReponseEleve')->findBy(array("eleve" => $reponseEleve->getEleve()->getId()));
        $score = 0;
        $count = 0;
        foreach ($resultList as $res) {
            $count++;
            $score = $score + $res->getScore();
        }
        if($count == 0)
            return $this -> redirectToRoute("listcoursetquiz");
        return $this->render('@activite/Questions/ResultQuizz.html.twig', array("score" => $score, "count" => $count * 10 ));

    }

    public function QuizResultPdfAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $reponseEleve = new ReponseEleve();
        $reponseEleve->setEleve( $this->container->get('security.token_storage')->getToken()->getUser());
        $resultList = $em->getRepository('activiteBundle:ReponseEleve')->findBy(array("eleve" => $reponseEleve->getEleve()->getId()));
        $score = 0;
        $count = 0;
        foreach ($resultList as $res) {
            $count++;
            $score = $score + $res->getScore();
        }
        if($count == 0)
            return $this -> redirectToRoute("listcoursetquiz");



        $snappy = new Pdf('C://wkhtmltopdf/bin/wkhtmltopdf.exe');
        $date = new \DateTime('now');
        $fileName = "result-".$reponseEleve->getEleve()->getUsername()." ".$date->format("yy-M-d Hms  ").".pdf" ;

        $html =  $this->render('@activite/Questions/ResultQuizzPdf.html.twig', array("eleve" =>$reponseEleve->getEleve() , "date" => $date->format("Y/M/d"), "score" => $score, "count" => $count * 10 ));

        //$snappy->generateFromHtml( $this->render('@activite/Questions/ResultQuizzPdf.html.twig', array("eleve" =>$reponseEleve->getEleve() , "date" => $date->format("Y/M/d"), "score" => $score, "count" => $count * 10 )) , "uploads/".$fileName);

        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'attachment; filename="'.$fileName.'.pdf"'
            )
        );
        // return $this->render('@activite/Questions/ResultQuizz.html.twig', array("score" => $score, "count" => $count * 10 , "res" => $fileName));

    }
}
