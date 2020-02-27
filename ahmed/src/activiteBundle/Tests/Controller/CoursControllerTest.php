<?php

namespace activiteBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class CoursControllerTest extends WebTestCase
{
    public function testAdd-our()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/add-our');
    }

    public function testListe_cour()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/liste_Cour');
    }

    public function testUpdate_cour()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/update_Cour');
    }

}
