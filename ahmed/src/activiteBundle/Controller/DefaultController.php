<?php

namespace activiteBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('activiteBundle:Default:list.html.twig');
    }
}
