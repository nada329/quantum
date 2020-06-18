<?php
namespace EventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * event
 *
 * @ORM\Table(name="event")
 * @ORM\Entity(repositoryClass="EventBundle\Repository\EventRepository")
 */
class event
{
    /**
     * @var int
     *
     * @ORM\Column(name="idmatiere", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idmatiere;

    /**
     * @var string
     *
     * @ORM\Column(name="nomMatiere", type="string", length=255)
     */
    private $Nommatiere;



    public function setCoefficient($Coefficient)
    {
        $this->oefficient = $oefficient;
    }
     /**
     *
     * @ORM\Column(name="coefficient",type="int", length=255)
     */




    private $Coefficient  ;


    /**
     * Set nomEvent
     *
     * @param string $nomMatiere
     *
     * @return event
     */
    public function setNomMatiere($nomMatiere)
    {
        $this->nomMatiere = $nomMatiere;

        return $this;
    }



}