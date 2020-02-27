<?php

namespace BibliothequeBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Livre
 *
 * @ORM\Table(name="livre")
 * @ORM\Entity(repositoryClass="BibliothequeBundle\Repository\LivreRepository")
 */
class Livre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 5,
     *      max = 255,
     *      minMessage = "Nom must be at least {{ limit }} characters long",
     *      maxMessage = "Nom cannot be longer than {{ limit }} characters"
     * )
     */
    private $nom;

    /**
     *
     * @ORM\Column(name="resume", type="string", length=5000)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 10,
     *      max = 5000,
     *      minMessage = "Resume must be at least {{ limit }} characters long",
     *      maxMessage = "Resume cannot be longer than {{ limit }} characters"
     * )
     */
    private $resume;

    /**
     *
     * @ORM\Column(name="photo", type="string", length=255)
     * @Assert\NotBlank
     */
    private $photo;

    /**
     * @return string
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * @param string $photo
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;
    }


    /**
     * @var string
     *
     * @ORM\Column(name="auteur", type="string", length=255)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 10,
     *      max = 255,
     *      minMessage = "Resume must be at least {{ limit }} characters long",
     *      maxMessage = "Resume cannot be longer than {{ limit }} characters"
     * )
     */
    private $auteur;

    /**
     * @var int
     *
     * @ORM\Column(name="Nb_de_pages", type="integer")
     */
    private $nbDePages;

    /**
     * @var string
     *
     * @ORM\Column(name="editur", type="string", length=255)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 10,
     *      max = 255,
     *      minMessage = "Editeur must be at least {{ limit }} characters long",
     *      maxMessage = "Editeur cannot be longer than {{ limit }} characters"
     * )
     */
    private $editur;

    /**
     * @ORM\Column(name="quantity", type="integer")
     */
    private $quantity;


    /**
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumn(name="id_categorie",referencedColumnName="id")
     */
    private $category;


    public function getQuantity()
    {
        return $this->quantity;
    }


    public function setQuantity($quantity)
    {
        $this->quantity = $quantity;
    }




    /**
     * @return mixed
     */
    public function getCategory()
    {
        return $this->category;
    }

    /**
     * @param mixed $category
     */
    public function setCategory($category)
    {
        $this->category = $category;
    }


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Livre
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set resume
     *
     * @param string $resume
     *
     * @return Livre
     */
    public function setResume($resume)
    {
        $this->resume = $resume;

        return $this;
    }

    /**
     * Get resume
     *
     * @return string
     */
    public function getResume()
    {
        return $this->resume;
    }

    /**
     * Set auteur
     *
     * @param string $auteur
     *
     * @return Livre
     */
    public function setAuteur($auteur)
    {
        $this->auteur = $auteur;

        return $this;
    }

    /**
     * Get auteur
     *
     * @return string
     */
    public function getAuteur()
    {
        return $this->auteur;
    }

    /**
     * Set nbDePages
     *
     * @param integer $nbDePages
     *
     * @return Livre
     */
    public function setNbDePages($nbDePages)
    {
        $this->nbDePages = $nbDePages;

        return $this;
    }

    /**
     * Get nbDePages
     *
     * @return int
     */
    public function getNbDePages()
    {
        return $this->nbDePages;
    }

    /**
     * Set editur
     *
     * @param string $editur
     *
     * @return Livre
     */
    public function setEditur($editur)
    {
        $this->editur = $editur;

        return $this;
    }

    /**
     * Get editur
     *
     * @return string
     */
    public function getEditur()
    {
        return $this->editur;
    }
}

