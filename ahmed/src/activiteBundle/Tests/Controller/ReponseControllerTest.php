<?php

namespace activiteBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class ReponseControllerTest extends WebTestCase
{
    public function testAddreponse()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/addReponse');
    }

}
